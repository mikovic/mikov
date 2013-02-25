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
     CREATE TABLE idea
     (
         idea_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
         user_id INT NOT NULL,
         CONSTRAINT users_user_id_fk
         FOREIGN KEY (user_id)
         REFERENCES users (user_id),
         topic_idea varchar(15),
         desc_idea varchar(30),
         create_date DATE,
         update_date DATE,
         close_date  DATE,
         budget  varchar(30)
      );
      CREATE TABLE categories
       (
         category_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
         category varchar(20)
        );
       CREATE TABLE comment
       (
         comment_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
         user_id INT NOT NULL,
         idea_id INT NOT NULL,
         CONSTRAINT FK_user_id  FOREIGN KEY(user_id)
         REFERENCES users (user_id),
         CONSTRAINT FK_idea_id  FOREIGN KEY(idea_id)
         REFERENCES idea (idea_id),
         text varchar(30),
         create_date DATE
        );
       CREATE TABLE invest
        (
         invest_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
         user_id INT NOT NULL,
         idea_id INT NOT NULL,
         FOREIGN KEY (user_id)
         REFERENCES users (user_id),
         FOREIGN KEY(idea_id)
         REFERENCES idea (idea_id),
         suminvest varchar(20)

         );