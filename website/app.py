import os
import json
from flask import Flask, render_template, jsonify, request
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

BASE_DIR = '/home/frictional/studies/JAVA'

def get_md_files():
    md_files = []
    for root, dirs, files in os.walk(BASE_DIR):
        # Skip website and venv directories
        dirs[:] = [d for d in dirs if d not in ['website', 'venv']]
        for file in files:
            if file.endswith('.md') or file.endswith('.java'):
                full_path = os.path.join(root, file)
                rel_path = os.path.relpath(full_path, BASE_DIR)
                md_files.append(rel_path)
    return sorted(md_files)

def read_file(rel_path):
    full_path = os.path.join(BASE_DIR, rel_path)
    with open(full_path, 'r', encoding='utf-8') as f:
        return f.read()

def write_file(rel_path, content):
    full_path = os.path.join(BASE_DIR, rel_path)
    with open(full_path, 'w', encoding='utf-8') as f:
        f.write(content)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/api/files')
def list_files():
    return jsonify(get_md_files())

@app.route('/api/file/<path:filepath>')
def get_file(filepath):
    try:
        content = read_file(filepath)
        return jsonify({'content': content})
    except Exception as e:
        return jsonify({'error': str(e)}), 404

@app.route('/api/file/<path:filepath>', methods=['POST'])
def save_file(filepath):
    try:
        data = request.get_json()
        content = data.get('content', '')
        write_file(filepath, content)
        return jsonify({'success': True})
    except Exception as e:
        return jsonify({'error': str(e)}), 500

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5000)
