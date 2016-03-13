1 constant [os]`
\ do dlopen now, and hook it to `boot to do dlopen for the turnkey case
: dlsetup libc@ 0- 0<> IF drop ;THEN drop "libc.so.6" #lib libc! ;
dlsetup
: libc.` wsparse libc@ #fun lit` #call ' call, ;
: libc_ libc@ #fun #call ; \ runtime version, turnkey safe

create `SEGVact 140 allot `SEGVact 140 0 fill
: `SEGVhndlr !"SEGV caught" ;
`SEGVhndlr ' `SEGVact!
$40000000 `SEGVact 132+ ! \ SA_NODEFER
: SEGVthrow 0 `SEGVact 11 3 "sigaction" libc_ drop ;
: linsetup dlsetup SEGVthrow ;

"/ff/" drop "HOME" 1_ libc. getenv
open' 1+ 2 libc. strcpy 2 libc. strcat 1 libc. strlen open' c!
"'ff.ff"
2dup openr close 0- drop 0< IF 0 open' !
2dup openr close 0- drop 0< IF 2drop "[os]" THEN THEN
needed ' ; linsetup ' ossetup !^ `boot ' >r `exec ;
