    CREATE DATABASE cassiopeya;
    USE cassiopeya;
    CREATE TABLE users
    (
        user_id INT NOT NULL AUTO_INCREMENT,
        userLogin varchar(20),
        password varchar(20),
        email varchar(30),
        PRIMARY KEY (user_id)
     )
     CREATE TABLE idea
     (
         idea_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
         user_id INT NOT NULL,
         CONSTRAINT users_user_id_fk
         FOREIGN KEY (user_id)
         REFERENCES users (user_id);
         desc_idea varchar(30),
         create_date DATE,
         update_date DATE,
         close_date  DATE,
         budget  varchar(30)
      )
      CREATE TABLE tag
       (
           tag_id INT NOT NULL AUTO_INCREMENT,
           idea_id INT NOT NULL,
           CONSTRAINT idea_idea_id_fk
           FOREIGN KEY (idea_id)
           REFERENCES idea (idea_id);
           tag_name varchar(20),
           PRIMARY KEY (tag_id)
        )
       CREATE TABLE comment
       (
          comment_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
          user_id INT NOT NULL,
          CONSTRAINT users_user_id_fk
          FOREIGN KEY (user_id)
          REFERENCES users (user_id);
          idea_id INT NOT NULL,
          CONSTRAINT idea_idea_id_fk
          FOREIGN KEY (idea_id)
          REFERENCES idea (idea_id);
          text varchar(30),
          create_date DATE
        )
        CREATE TABLE invest
        (
            invest_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
            user_id INT NOT NULL,
            CONSTRAINT users_user_id_fk
            FOREIGN KEY (user_id)
            REFERENCES users (user_id);
            idea_id INT NOT NULL
            CONSTRAINT idea_idea_id_fk
            FOREIGN KEY (idea_id)
            REFERENCES idea (idea_id);
            summ varchar(20)

        )