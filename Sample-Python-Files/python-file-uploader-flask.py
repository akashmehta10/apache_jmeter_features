from flask import Flask, render_template, request
from werkzeug.utils import secure_filename
from werkzeug.datastructures import  FileStorage
import json

app = Flask(__name__)

@app.route('/uploader', methods = ['GET', 'POST'])
def upload_file():
    if request.method == 'POST':
        data = request.files.get('file')
        response = {'msg': 'success','content_type': request.content_type,'content_length': request.content_length}
        return json.dumps(response)

if __name__ == '__main__':
    app.run(debug = True)