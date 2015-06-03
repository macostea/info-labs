@echo off
for /f "tokens=*" %%f in ('dir /b *.txt') do (
    rename %%f %%~nf.log
)