#changing corps permission
application.properties : app.access.allowOrigin = http://localhost:4020

#creating database
spring.datasource.url = jdbc:mysql://localhost:3306/bs_admin
spring.datasource.username = root
spring.datasource.password = 

#add storage for uploading files
app.post.storageLocation=~/Documents/AdminPanel/admin-panel-back-end/run/upload-dir
