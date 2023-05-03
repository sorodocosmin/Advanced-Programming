select now();

-- DROPING TABLES
drop table if exists album_has_genres;
drop table if exists album_has_artists;

drop table if exists albums;
drop table if exists genres;
drop table if exists artists;



--CREATING TABLES

Create table albums (
    id serial PRIMARY KEY ,
    release_year INTEGER NOT NULL ,
    title varchar(100) NOT NULL
);


Create table artists (
    id serial PRIMARY KEY ,
    name varchar(100) NOT NULL
);

Create table genres (
    id serial PRIMARY KEY ,
    name varchar(100) NOT NULL
);

Create table album_has_genres (
    id serial PRIMARY KEY,
    id_album INTEGER,
    id_genre INTEGER,
    FOREIGN KEY (id_album) REFERENCES albums(id) ON DELETE CASCADE,
    FOREIGN KEY (id_genre) REFERENCES genres(id) ON DELETE CASCADE
);

Create table album_has_artists (
    id serial PRIMARY KEY,
    id_album INTEGER,
    id_artist INTEGER,
    FOREIGN KEY (id_album) REFERENCES albums(id) ON DELETE CASCADE,
    FOREIGN KEY (id_artist) REFERENCES artists(id) ON DELETE CASCADE
);


--QUERIES
select * from albums;

select * from artists;

select * from genres;

select * from album_has_genres;

select * from album_has_artists;

delete from albums;
delete from artists;
delete from genres;


select name from artists where id = 1;

select a.title AS "Title Albun", a.release_year as "Release Year", artists.name AS "Artist Name", genres.name AS "Genre Music"
from albums a JOIN album_has_artists a_h_a ON a.id = a_h_a.id_album
JOIN artists ON a_h_a.id_artist = artists.id
JOIN album_has_genres a_h_g ON a.id = a_h_g.id_album
JOIN genres ON a_h_g.id_genre = genres.id;

--INSERTION



--insert albums
INSERT INTO albums (release_year, title) VALUES ('2006-01-01','Back to Black');
INSERT INTO albums (release_year, title) VALUES ('2014-01-01','1989');
INSERT INTO albums (release_year, title) VALUES ('1982-01-01','Thriller');
INSERT INTO albums (release_year, title) VALUES ('2016-01-01','Lemonade');
INSERT INTO albums (release_year, title) VALUES ('2015-01-01','Beauty Behind the Madness');
INSERT INTO albums (release_year, title) VALUES ('2016-01-01','A Seat at the Table');
INSERT INTO albums (release_year, title) VALUES ('2016-01-01','Views');
INSERT INTO albums (release_year, title) VALUES ('2011-01-01','Watch the Throne');
INSERT INTO albums (release_year, title) VALUES ('2012-01-01','The Heist');
INSERT INTO albums (release_year, title) VALUES ('2016-01-01','Stoney');


--insert the artists
INSERT INTO artists (name) values ('Amy Winehouse');
INSERT INTO artists (name) values ('Taylor Swift');
INSERT INTO artists (name) values ('Michael Jackson');
INSERT INTO artists (name) values ('Beyoncé');
INSERT INTO artists (name) values ('The Weeknd');
INSERT INTO artists (name) values ('Solange');
INSERT INTO artists (name) values ('Jay-Z');
INSERT INTO artists (name) values ('Kanye West');
INSERT INTO artists (name) values ('Macklemore');
INSERT INTO artists (name) values ('Ryan Lewis');
INSERT INTO artists (name) values ('Post Malone');
INSERT INTO artists (name) values ('Drake');


--insert the genres

INSERT INTO genres (name) VALUES ('Soul');
INSERT INTO genres (name) VALUES ('R&B');
INSERT INTO genres (name) VALUES ('Pop');
INSERT INTO genres (name) VALUES ('Funk');
INSERT INTO genres (name) VALUES ('Hip Hop');
INSERT INTO genres (name) VALUES ('Rap');


--insert correlation between albums and genres

INSERT INTO album_has_genres (id_album, id_genre) VALUES (1,1);
INSERT INTO album_has_genres (id_album, id_genre) VALUES (1,2);

INSERT INTO album_has_genres (id_album, id_genre) VALUES (2,3);

INSERT INTO album_has_genres (id_album, id_genre) VALUES (3,3);
INSERT INTO album_has_genres (id_album, id_genre) VALUES (3,4);

INSERT INTO album_has_genres (id_album, id_genre) VALUES (4,2);

INSERT INTO album_has_genres (id_album, id_genre) VALUES (5,2);
INSERT INTO album_has_genres (id_album, id_genre) VALUES (5,3);

INSERT INTO album_has_genres (id_album, id_genre) VALUES (6,1);
INSERT INTO album_has_genres (id_album, id_genre) VALUES (6,2);

INSERT INTO album_has_genres (id_album, id_genre) VALUES (7,5);
INSERT INTO album_has_genres (id_album, id_genre) VALUES (7,6);

INSERT INTO album_has_genres (id_album, id_genre) VALUES (8,5);

INSERT INTO album_has_genres (id_album, id_genre) VALUES (9,5);

INSERT INTO album_has_genres (id_album, id_genre) VALUES (10,5);
INSERT INTO album_has_genres (id_album, id_genre) VALUES (10,2);



--insert artists for each album

INSERT INTO album_has_artists (id_album, id_artist) VALUES (1,1);

INSERT INTO album_has_artists (id_album, id_artist) VALUES (2,2);

INSERT INTO album_has_artists (id_album, id_artist) VALUES (3,3);

INSERT INTO album_has_artists (id_album, id_artist) VALUES (4,4);

INSERT INTO album_has_artists (id_album, id_artist) VALUES (5,5);

INSERT INTO album_has_artists (id_album, id_artist) VALUES (6,6);

INSERT INTO album_has_artists (id_album, id_artist) VALUES (7,12);

INSERT INTO album_has_artists (id_album, id_artist) VALUES (8,7);
INSERT INTO album_has_artists (id_album, id_artist) VALUES (8,8);

INSERT INTO album_has_artists (id_album, id_artist) VALUES (9,9);
INSERT INTO album_has_artists (id_album, id_artist) VALUES (9,10);

INSERT INTO album_has_artists (id_album, id_artist) VALUES (10,11);











