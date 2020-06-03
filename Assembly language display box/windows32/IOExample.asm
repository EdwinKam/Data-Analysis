;final Edwin Kam


.586
.MODEL FLAT

INCLUDE io.h            ; header file for input/output

.STACK 4096

.DATA 
number1 DWORD   ?
number2 DWORD   ?
count DWORD   ?
array	DWORD		1000 DUP (?)	; the array (100 max elements)
prompt1 BYTE    "Enter a number", 0
prompt2 BYTE    "Enter 10 numbers to set up the array", 0


string  BYTE    40 DUP (?)
resultLbl BYTE  "Number of numbers matched", 0
noleap BYTE  "this is not a leap year", 0
sum     BYTE    11 DUP (?), 0

.CODE
_MainProc PROC
        


		mov ecx, 10
		lea ebx, array	;set up ebx
create:	
		input   prompt2, string, 40      ; read ASCII characters
        atod    string          ; convert to integer
		mov [ebx], eax	;put the number into the array
		add ebx, 4	;inc ebx to next box
		loop create

		input   prompt1, string, 40      ; read ASCII characters
        atod    string          ; convert to integer
        mov     number1, eax    ; store in memory

		mov count, 0		;initialize count
		mov ecx, 10
		lea ebx, array	;move ebx to first address
check:	
		call compare
		add ebx, 4	;inc ebx to next box
		loop check

		dtoa    sum, count  ; convert to ASCII characters
        output  resultLbl, sum  ; output label and sum


compare PROC
	cmp eax,[ebx]		;cmp user input and array number
	jz match		;jmp to match to inc the count
	ret
match: inc count	;inc count
		ret	;return back to main
compare ENDP
_MainProc ENDP
END                             ; end of source code
