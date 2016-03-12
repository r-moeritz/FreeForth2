1 constant [os]`
\ do dlopen now, and hook it to `boot to do dlopen for the turnkey case
: dlsetuplin libc@ 0- 0<> IF drop ;THEN drop "libc.so.6" #lib libc! ;
dlsetuplin
: libc.` wsparse libc@ #fun lit` #call ' call, ;
: libc_ libc@ #fun #call ; \ runtime version, turnkey safe
"/ff/" drop "HOME" 1_ libc. getenv
open' 1+ 2 libc. strcpy 2 libc. strcat 1 libc. strlen open' c!
"'ff.ff"
2dup openr close 0- drop 0< IF 0 open' !
2dup openr close 0- drop 0< IF 2drop "[os]" THEN THEN
needed ' ; dlsetuplin ' dlsetup !^ `boot ' >r `exec ;
