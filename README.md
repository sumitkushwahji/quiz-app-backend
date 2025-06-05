# quiz-app-backend


build jar file
./mvnw clean package -DskipTests


build docker image
docker build -t quiz-app-backend .


run docker file
docker run -d -p 8080:8080 --name quiz-app-backend quiz-app-backend

to add roles manually
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

alter table if required
ALTER TABLE question ALTER COLUMN text TYPE TEXT;
ALTER TABLE question ALTER COLUMN explanation TYPE TEXT;
ALTER TABLE option ALTER COLUMN text TYPE TEXT;
