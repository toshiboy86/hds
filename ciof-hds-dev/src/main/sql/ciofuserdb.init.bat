:: ciofuserdb.create.bat
:: (C) 2019. Industrial Value Chain Initiative
@echo off

set HERE=%~dp0

set POSTGRES_9_5_HOME=C:\Program Files\PostgreSQL\9.5
set POSTGRES_9_6_HOME=C:\Program Files\PostgreSQL\9.6
set PATH=%POSTGRES_9_5_HOME%\bin;%POSTGRES_9_6_HOME%\bin;%PATH%

set PSQL_OPTS=-h localhost -d ciofuserdb -U postgres 

psql %PSQL_OPTS% -f "%HERE%\ciofuserdb.init.sql"

pause
