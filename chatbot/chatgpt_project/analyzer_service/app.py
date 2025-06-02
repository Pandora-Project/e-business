from flask import Flask, request, jsonify, send_from_directory
from flask_cors import CORS
import requests
import os

app = Flask(__name__)
CORS(app)


@app.route('/analyze', methods=['POST'])
def analyze():
    data = request.get_json()
    user_text = data.get('text')
    try:
        response = requests.post(
            'http://localhost:5002/chat', json={'prompt': user_text})
        response.raise_for_status()
        return jsonify(response.json())
    except Exception as e:
        return jsonify({'error': 'Chat service unavailable or returned invalid response', 'details': str(e)}), 500


@app.route('/')
def serve_index():
    frontend_dir = os.path.abspath(os.path.join(
        os.path.dirname(__file__), '..', 'frontend'))
    return send_from_directory(frontend_dir, 'index.html')


if __name__ == '__main__':
    app.run(port=5001)
