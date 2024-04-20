# Doro 🔥

A simple MVC based "Posting" or twitter clone web app built using (Jakarta EE 10):

- Servlet
- JSP
- Bootsrap
- JDBC

It has :

- Login
- Registration
- ROLE based registration,
- Posting or tweeting
- Following users
- Liking Posts
- Route protection and
- Password hashing

## Getting started with poject

create a database called doro and create the necesary tables with the following code.

```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    role ENUM('user', 'admin') DEFAULT 'user',
    password VARCHAR(255) NOT NULL
);

CREATE TABLE posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    image TEXT,
    user INT NOT NULL,
    FOREIGN KEY (user) REFERENCES users(id)
);

CREATE TABLE followers (
    user INT,
    follows INT,
    PRIMARY KEY (user, follows),
    FOREIGN KEY (user) REFERENCES users(id),
    FOREIGN KEY (follows) REFERENCES users(id)
);

CREATE TABLE likes (
    user INT,
    post INT,
    PRIMARY KEY (user, post),
    FOREIGN KEY (user) REFERENCES users(id),
    FOREIGN KEY (post) REFERENCES posts(id)
);
```

then build the war file for the project

```bash
mvn clean package
```

then deploy the war file to tomcat webapps.
