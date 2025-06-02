from flask import Flask, request, jsonify
import openai

app = Flask(__name__)
openai.api_key = 'api-key'


@app.route('/chat', methods=['POST'])
def chat():
    data = request.get_json()
    prompt = data.get('prompt')
    try:
        response = openai.chat.completions.create(
            model="gpt-4.1",
            messages=[
                {"role": "user", "content": prompt}
            ],
            max_tokens=150
        )
        return jsonify({'response': response.choices[0].message.content.strip()})
    except Exception as e:
        return jsonify({'error': str(e)}), 500


if __name__ == '__main__':
    app.run(port=5002)
