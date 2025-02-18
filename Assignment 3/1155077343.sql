/* Query 1 */
SELECT L1.LID, L1.LEAGUE_NAME, L1.SEASON, T1.TEAM_NAME
FROM LEAGUES L1, TEAMS T1
WHERE L1.CHAMPION_TID = T1.TID
      AND T1.AVERAGE_AGE > ANY (SELECT T2.AVERAGE_AGE
                                FROM TEAMS T2)
ORDER BY L1.LID ASC;

/* Query 2 */
SELECT T2.TID, T2.TEAM_NAME, L2.LEAGUE_NAME, L2.SEASON
FROM TEAMS T2, LEAGUES L2
WHERE L2.CHAMPION_TID = T2.TID AND (L2.YEAR = '2016' OR L2.YEAR = '2017')
ORDER BY T2.TID ASC;

/* Query 3 */
SELECT W.W_NUM, T3.TID, T3.TEAM_NAME
FROM TEAMS T3
INNER JOIN (SELECT CHAMPION_TID, COUNT(*) AS W_NUM
            FROM LEAGUES L3
            GROUP BY L3.CHAMPION_TID
            ORDER BY COUNT(*) DESC) W ON W.CHAMPION_TID = T3.TID AND W.W_NUM >= 2
ORDER BY W.W_NUM DESC;

/* Query 4 */
SELECT DISTINCT R4.FOOTBALL_RANKING, R4.RID, R4.REGION_NAME
FROM REGIONS R4
INNER JOIN (SELECT L4.RID, L4.SEASON, COUNT(*) AS NUM
            FROM LEAGUES L4
            GROUP BY L4.RID, L4.SEASON) X ON X.NUM > 2 AND X.RID = R4.RID
ORDER BY R4.FOOTBALL_RANKING ASC;

/* QUERY 5 */
SELECT Y.S_COUNT, S4.SID, S4.SPONSOR_NAME
FROM SPONSORS S4
INNER JOIN (SELECT S5.SID, COUNT(*) AS S_COUNT
            FROM SUPPORT S5
            GROUP BY S5.SID) Y ON Y.SID = S4.SID
ORDER BY S_COUNT DESC;

/* QUERY 6 */
SELECT S6.SID, S6.SPONSOR_NAME, S6.MARKET_VALUE
FROM SPONSORS S6
INNER JOIN (SELECT P6.SID, SUM(P6.SPONSORSHIP) AS S_SUM
            FROM SUPPORT P6
            GROUP BY P6.SID) Z ON Z.S_SUM > 3.0 AND S6.SID = Z.SID AND S6.MARKET_VALUE > 100
ORDER BY S6.SID ASC;

/* QUERY 7 */
SELECT S8.SID, S8.SPONSOR_NAME
FROM SPONSORS S8
INNER JOIN (SELECT DISTINCT COUNT(*) AS NUM, P7.SID
            FROM SUPPORT P7
            INNER JOIN (SELECT L7.LID
                        FROM LEAGUES L7, REGIONS R7
                        WHERE L7.RID = R7.RID AND R7.REGION_NAME != 'Japan') A ON A.LID = P7.LID
            GROUP BY P7.SID) B ON S8.SID = B.SID AND B.NUM >= 2
ORDER BY S8.SID ASC;

/* QUERY 8 */
CREATE VIEW ACTIVE AS
SELECT P8.SID, COUNT(*) AS ACTIVE_NUM
FROM SUPPORT P8
GROUP BY P8.SID;

CREATE VIEW ACTIVE_LID AS
SELECT P9.LID
FROM SUPPORT P9
WHERE P9.SID = (SELECT A1.SID
                FROM ACTIVE A1
                WHERE A1.ACTIVE_NUM >= ALL (SELECT A2.ACTIVE_NUM
                                            FROM ACTIVE A2));

SELECT R8.RID, R8.REGION_NAME
FROM REGIONS R8
INNER JOIN (SELECT DISTINCT L8.RID
            FROM ACTIVE_LID AL1, LEAGUES L8
            WHERE AL1.LID = L8.LID) D ON D.RID = R8.RID
ORDER BY R8.RID DESC;

DROP VIEW ACTIVE;
DROP VIEW ACTIVE_LID;
