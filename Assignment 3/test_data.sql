drop table SUPPORT;
drop table SPONSORS;
drop table LEAGUES;
drop table TEAMS;
drop table REGIONS;

create table SPONSORS(
    SID			Integer,
    SPONSOR_NAME	varchar2(30),
    MARKET_VALUE	Float,
    Primary Key (SID)
);

create table TEAMS(
    TID			Integer,
    TEAM_NAME		varchar2(30),
    AVERAGE_AGE		Float,
    Primary Key (TID)
);

create table REGIONS(
    RID			Integer,
    REGION_NAME	varchar2(30),
    FOOTBALL_RANKING	Integer,
    Primary Key (RID)
);

create table LEAGUES(
    LID			Integer,
    LEAGUE_NAME		varchar2(30),
    CHAMPION_TID	Integer,
    YEAR		Integer,
    SEASON		varchar2(10),
    RID			Integer,
    Primary Key (LID),
    FOREIGN KEY(CHAMPION_TID) REFERENCES TEAMS(TID),
    FOREIGN KEY(RID) REFERENCES REGIONS(RID)
);

create table SUPPORT(
    LID			Integer,
    SID			Integer,
    SPONSORSHIP		Float,
    Primary Key (LID, SID),
    FOREIGN KEY(LID) REFERENCES LEAGUES(LID),
    FOREIGN KEY(SID) REFERENCES SPONSORS(SID)
);

insert into SPONSORS values( 1, 'Koka-Kola',105 );
insert into SPONSORS values( 2, 'SOLY', 50 );
insert into SPONSORS values( 3, 'Panesony', 68 );
insert into SPONSORS values( 4, 'Appla', 150);
insert into SPONSORS values( 5, 'McDonna', 980);
insert into SPONSORS values( 6, 'Pizza LA', 31);
insert into SPONSORS values( 7, '4HK', 17);
insert into SPONSORS values( 8, 'Hennikan', 155);
insert into SPONSORS values( 9, 'Soga', 39);
insert into SPONSORS values( 10,'StarDuck',66);
insert into SPONSORS values( 11,'Moncow', 98);

insert into TEAMS values( 1, 'South Hong Kong', 30 );
insert into TEAMS values( 2, 'Dai Lin', 28.7);
insert into TEAMS values( 3, 'Shanghai', 27);
insert into TEAMS values( 4, 'Men United', 29);
insert into TEAMS values( 5, 'Old Castle', 35);
insert into TEAMS values( 6, 'AD Millan', 29);
insert into TEAMS values( 7, 'Tokyo Sakura', 25);
insert into TEAMS values( 8, 'Osaka Hana', 32.4);
insert into TEAMS values( 9, 'Curtis-Wsagor', 24);
insert into TEAMS values( 10, 'Royal Madirid', 35 );
insert into TEAMS values( 11, 'Byan Muni', 30 );
insert into TEAMS values( 12, 'Chicago Cow', 24 );

insert into REGIONS values( 1, 'China', 100);
insert into REGIONS values( 2, 'Japan', 30);
insert into REGIONS values( 3, 'England', 10);
insert into REGIONS values( 4, 'Brazil', 3);
insert into REGIONS values( 5, 'Spain', 1);
insert into REGIONS values( 6, 'Germany', 8);
insert into REGIONS values( 7, 'United States', 50);
insert into REGIONS values( 8, 'Italy', 9);
insert into REGIONS values( 9, 'Hong Kong', 150);

insert into LEAGUES values( 1, 'Hong Kong FootBall League', 1, 2015, 'Autumn', 9 );
insert into LEAGUES values( 2, 'Hong Kong FootBall League', 1, 2016, 'Autumn', 9 );
insert into LEAGUES values( 3, 'Hong Kong FootBall League', 1, 2017, 'Autumn', 9 );
insert into LEAGUES values( 4, 'England Super League', 5, 2015, 'Summer', 3 );
insert into LEAGUES values( 5, 'England Super League', 4, 2016, 'Summer', 3 );
insert into LEAGUES values( 6, 'England Super League', 5, 2017, 'Summer', 3 );
insert into LEAGUES values( 7, 'England Winter League', 4, 2015, 'Winter', 3 );
insert into LEAGUES values( 8, 'England Winter League', 5, 2016, 'Winter', 3 );
insert into LEAGUES values( 9, 'England Winter League', 4, 2017, 'Winter', 3 );
insert into LEAGUES values( 10, 'China People League', 3, 2015, 'Spring', 1 );
insert into LEAGUES values( 11, 'China People League', 2, 2016, 'Spring', 1 );
insert into LEAGUES values( 12, 'China People League', 1, 2017, 'Spring', 1 );
insert into LEAGUES values( 13, 'J-League', 7, 2015, 'Autumn', 2 );
insert into LEAGUES values( 14, 'J-League', 7, 2016, 'Autumn', 2 );
insert into LEAGUES values( 15, 'J-League', 8, 2017, 'Spring', 2 );
insert into LEAGUES values( 16, 'Germany First Class League', 11, 2016, 'Summer', 6 );
insert into LEAGUES values( 17, 'Spain A Class League', 10, 2016, 'Autumn', 5 );
insert into LEAGUES values( 18, 'Brazil Summer League', 9, 2016, 'Summer', 4 );
insert into LEAGUES values( 19, 'Italy League', 6, 2016, 'Spring', 8 );
insert into LEAGUES values( 20, 'USA National League', 12, 2016, 'Spring', 7 );

insert into SUPPORT values ( 1, 1, 0.05 );
insert into SUPPORT values ( 3, 1, 0.1 );
insert into SUPPORT values ( 5, 1, 0.15 );
insert into SUPPORT values ( 2, 2, 0.35 );
insert into SUPPORT values ( 20, 2, 0.45 );
insert into SUPPORT values ( 4, 2, 0.95 );
insert into SUPPORT values ( 2, 3, 1.3 );
insert into SUPPORT values ( 19, 3, 0.04 );
insert into SUPPORT values ( 18, 3, 0.45 );
insert into SUPPORT values ( 19, 4, 0.55 );
insert into SUPPORT values ( 2, 4, 0.85 );
insert into SUPPORT values ( 6, 4, 2.1 );
insert into SUPPORT values ( 14, 5, 1.2 );
insert into SUPPORT values ( 19, 6, 0.78 );
insert into SUPPORT values ( 2, 6, 0.55 );
insert into SUPPORT values ( 10, 6, 0.43 );
insert into SUPPORT values ( 11, 6, 0.87 );
insert into SUPPORT values ( 12, 6, 1.11 );
insert into SUPPORT values ( 1, 7, 0.76 );
insert into SUPPORT values ( 2, 7, 0.23 );
insert into SUPPORT values ( 3, 7, 0.48 );
insert into SUPPORT values ( 8, 8, 1.56 );
insert into SUPPORT values ( 9, 8, 0.95 );
insert into SUPPORT values ( 10, 8, 0.27 );
insert into SUPPORT values ( 11, 8, 0.36 );
insert into SUPPORT values ( 12, 8, 0.45 );
insert into SUPPORT values ( 13, 8, 0.56 );
insert into SUPPORT values ( 19, 9, 0.35 );
insert into SUPPORT values ( 2, 9, 1.22 );
insert into SUPPORT values ( 13, 9, 0.78 );
insert into SUPPORT values ( 14, 9, 0.14 );
insert into SUPPORT values ( 15, 9, 0.86 );
insert into SUPPORT values ( 19, 10, 0.45 );
insert into SUPPORT values ( 19, 11, 0.12 );
