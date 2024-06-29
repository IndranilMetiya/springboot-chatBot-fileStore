# springboot-chatBot-fileStore
This is a integration of Springboot with google gemini chatBot and store all chats on file

curl \
  -H 'Content-Type: application/json' \
  -d '{"contents":[{"parts":[{"text":"How to learn Springboot"}]}]}' \
  -X POST 'https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=<Enter Your API Key>'

In this project we can request anything and get response by integrating google gemini which is a free tool, and also implemented filter as well as store all the chat with respect to each session in files. 
