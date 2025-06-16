require('dotenv').config()
const express = require('express')
const mongoose = require('mongoose')
const passport = require('passport')
const session = require('express-session')
const jwt = require('jsonwebtoken')
const cors = require('cors')
require('./auth')

const app = express()

// MongoDB
mongoose.connect(process.env.MONGO_URI).then(() => console.log('MongoDB connected'))

// Middlewares
app.use(cors({
  origin: process.env.CLIENT_URL,
  credentials: true,
}))
app.use(session({ secret: 'secret', resave: false, saveUninitialized: true }))
app.use(passport.initialize())

// Routes
app.get('/api/auth/google', passport.authenticate('google', {
  scope: ['profile', 'email'],
}))

app.get('/api/auth/google/callback', passport.authenticate('google', {
  session: false,
  failureRedirect: '/',
}), (req, res) => {
  const token = jwt.sign({ id: req.user._id }, process.env.JWT_SECRET, { expiresIn: '1h' })
  res.redirect(`${process.env.CLIENT_URL}/oauth-callback?token=${token}`)
})

// Protected example route
app.get('/api/profile', (req, res) => {
  const authHeader = req.headers.authorization
  if (!authHeader) return res.sendStatus(401)
  const token = authHeader.split(' ')[1]
  jwt.verify(token, process.env.JWT_SECRET, (err, user) => {
    if (err) return res.sendStatus(403)
    res.json({ message: 'Secure data', user })
  })
})

// Start
app.listen(process.env.PORT, () => console.log(`Server running on http://localhost:${process.env.PORT}`))
