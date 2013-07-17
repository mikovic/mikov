    CREATE DATABASE cassiopeya;
    USE cassiopeya;
    CREATE TABLE users
    (
        user_id INT NOT NULL AUTO_INCREMENT,
        user_login varchar(20),
        password varchar(20),
        email varchar(30),
        PRIMARY KEY (user_id)
     );
     CREATE TABLE ideas
     (
         idea_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
         user_id INT NOT NULL,
         CONSTRAINT users_user_id_fk
         FOREIGN KEY (user_id)
         REFERENCES users (user_id),
         category_id varchar(20),
         topic_idea varchar(15),
         desc_idea blob,
         create_date DATE,
         update_date DATE,
         close_date  DATE,
         budget  int(11)
      );
      CREATE TABLE categories
       (
         category_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
         category_name varchar(20)
        );
       CREATE TABLE comments
       (
         comment_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
         user_id INT NOT NULL,
         idea_id INT NOT NULL,
         CONSTRAINT FK_user_id  FOREIGN KEY(user_id)
         REFERENCES users (user_id),
         CONSTRAINT FK_idea_id  FOREIGN KEY(idea_id)
         REFERENCES ideas (idea_id),
         text varchar(250),
         create_date varchar(10)
        );
       CREATE TABLE invests
        (
         invest_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
         user_id INT NOT NULL,
         idea_id INT NOT NULL,
         FOREIGN KEY (user_id)
         REFERENCES users (user_id),
         FOREIGN KEY(idea_id)
         REFERENCES ideas (idea_id),
         investment int(11) NOT NULL DEFAULT 0;
         invest_date DATE
         );
         CREATE TABLE images
         (
         img_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
         idea_id INT NOT NULL,
         user_id INT NOT NULL,
         FOREIGN KEY(idea_id)
         REFERENCES ideas (idea_id),
         FOREIGN KEY (user_id)
         REFERENCES users (user_id),
         path_img VARCHAR(250)

         );