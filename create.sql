CREATE SEQUENCE s_profile;

CREATE SEQUENCE s_movie;

create table profile (
   	id_profile           	BIGINT               not null DEFAULT (nextval('s_profile')),
   	surname_profile         VARCHAR(100)         not null,
   	name_profile            VARCHAR(100)         not null,
   	birthday_profile        DATE                 not null,
   	phone_profile    		VARCHAR(20)          not null UNIQUE,
   	email_profile     		VARCHAR(100)         null UNIQUE,
	password_profile		VARCHAR(100)         null	
);

create table movie (
   	id_movie           	BIGINT              not null DEFAULT (nextval('s_movie')),
   	title				VARCHAR(100)		not null,
	release_year		INT					not null,
	id_profile			BIGINT              not null
);

alter table profile
   add constraint PK_PROFILE primary key (id_profile);
   
alter table movie
   add constraint PK_movie primary key (id_movie);

alter table movie
   add constraint FK_MOVIE_PROFILE foreign key (id_profile)
      references profile (id_profile)
      on delete cascade on update cascade;
	  