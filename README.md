### cURL de geração do token
````
curl --location --request POST 'http://127.0.0.1:8080/authenticate' \
--header 'Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=' \
--header 'Cookie: JSESSIONID=F6C451CD3670E015870E9CEFFF128519'
````

### cURL do recurso privado passando o token no headers
````
curl --location 'http://127.0.0.1:8080/private' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzcHJpbmctc2VjdXJpdHktd2l0aC1qd3QtZ2l1Iiwic3ViIjoidXNlcm5hbWUiLCJzY29wZXMiOiJyZWFkIiwiZXhwIjoxNzA2NTU1OTA4LCJpYXQiOjE3MDY1NTIzMDh9.ULcK7oAXzf2aBwb3RZkvh5fEwyjHbpUZ1u-ksunE4pHoDhEUlmoO-4p32-ls4ujG5AOcFDJqsf6f_Dgyv-RnamBPI9CbXz1cZbh8lE9Oy1jgYusEZct8zdWuItYuUmN8-Y8m3voTCwtNEIQUoghlDb69IdsqK29AcFz2h3DAuqNzmimrDtgVODlSYJqBcm3x6xMLlWOihZBpXfqYHm48zflWG-sRWOjzCdHlw2Mi9UDnHwiYdluKJggi_7XljEjUxHJKayguqcmQ5q_JRdk7LYR5qZ_jerqtrcH7p3VkydEQLlxxrIyoe9CV_Y7JVUWdsoLwRpAQJiphaMkRIWkAFA' \
--header 'Cookie: JSESSIONID=F6C451CD3670E015870E9CEFFF128519'
````