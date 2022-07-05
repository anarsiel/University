global  _print
        
section .data
ten:        DB      10
sixteen:    DB      16
number:     DD      0, 0, 0, 0 ; base = 2 ^ 32, 1000 = 1, 0100 = 2^32, ...
zero:       DB      0
sign:       DD      0 ; 0 -positive, 1 - negative
number_len: DD      0

;flags
minus_flag:  DD      0
plus_flag:   DD      0
space_flag:  DD      0
zero_flag:   DD      0
width_field: DD      0
        
section .text
_print:
        push      eax
        push      ebx
        push      esi
        push      edi
        push      ecx

        mov       esi, [esp + 4  + 5 * 4] ; out
        mov       edi, [esp + 8  + 5 * 4] ; first format char
        mov       ebx, [esp + 12 + 5 * 4] ; first string char

        call     zeroize

		call     get_input_sign        
        call     read_hex_and_convert
        call     get_arithmetic_sign
        xor      [sign], eax
		call     number_abs
		call     parse_flags
		
		cmp      byte [minus_flag], 1
		jne      rightAlign
		
		call     left_align
		
		jmp      leftAlign
		rightAlign:
		call     right_align
		leftAlign:
		call 	  check_for_zero
        
        pop       ecx
        pop       edi
        pop       esi
        pop       ebx
        pop       eax
        ret

zeroize:
		mov      byte [number], 0
		mov      byte [number + 4], 0
		mov      byte [number + 8], 0
		mov      byte [number + 12], 0

		mov      byte [zero], 0
		mov      byte [sign], 0
		mov      byte [number_len], 0

		mov      byte [minus_flag], 0
		mov      byte [plus_flag], 0
		mov      byte [space_flag], 0
		mov      byte [zero_flag], 0
		mov      byte [width_field], 0
		ret

left_align:
	call     add_sign
    push esi
    call     generate_width
    call     print_number
    pop esi
    call     reverse
	ret

right_align:
	cmp byte [zero_flag], 1
	jne hasntZero

	push esi
	call print_number
	call generate_width
	call add_sign
	pop esi
	call reverse
	

	jmp endRA
	hasntZero:

	push esi
	call print_number
	call add_sign
	call generate_width
	pop esi
	call reverse
	endRA:
	ret

; number - input
; eax    - number's lenght
get_number_len:
        push      dword [number]
        push      dword [number+4]
        push      dword [number+8]
        push      dword [number+12]
        push      edi

        xor       eax, eax
        start_printing2:
        cmp dword       [number], 0
        jne             print_digit2
        cmp dword       [number + 4], 0
        jne             print_digit2
        cmp dword       [number + 8], 0
        jne             print_digit2
        cmp dword       [number + 12], 0
        jne             print_digit2
        jmp             stop_printing2

        print_digit2:
        inc       eax
        call      decimal_to_out2
        jmp       start_printing2
        stop_printing2:

        ;mov      edi, eax
        ;call     sign_needed

        ;add      eax, edi

        pop      edi
        pop      dword [number+12]
        pop      dword [number+8]
        pop      dword [number+4]
        pop      dword [number]
		ret

; esi - pointer to generate
generate_width:
	push eax
	push ebx
	
	mov ebx, ' '
	cmp byte [zero_flag], 1
	jne useSpaces
	mov ebx, '0'
	useSpaces:

	mov eax, [width_field]
	continue_gen:
	test eax, eax
	je stop_gen
	
	mov [esi], ebx
	inc esi
	dec eax

	jmp continue_gen
	stop_gen:

	pop ebx
	pop eax
	ret

; eax - 1 if needed, else 0
sign_needed:
		xor       eax, eax
		cmp	      byte [sign], 1
        jne       isPositive2
        mov       eax, 1
        jmp       isNegative2
        isPositive2:
        cmp	      byte [plus_flag], 1
        jne       plusIsNotNeeded2
        mov       eax, 1
        plusIsNotNeeded2:
        cmp	      byte [space_flag], 1
        jne       isNegative2
        mov       eax, 1
        isNegative2:
		ret

; esi = pointer to out
; result - added sign and esi++ if needed
add_sign:
		cmp	      byte [sign], 1
        jne       isPositive
        mov       byte [esi], '-'
        inc       esi
        jmp       isNegative
        isPositive:
        cmp	      byte [plus_flag], 1
        jne       plusIsNotNeeded
        mov       byte [esi], '+'
        inc       esi
        plusIsNotNeeded:
        cmp	      byte [space_flag], 1
        jne       isNegative
        mov       byte [esi], ' '
        inc       esi
        isNegative:
		ret

; ebx    - first string char
; result - number (in dec) 
read_hex_and_convert:
		push      esi
		push      edi
		push      ebx
		push      ecx

        xor       edi, edi
        xor       ecx, ecx
        xor       eax, eax
        .continue:
        movzx     edi, byte [ebx]          ; loading one char to edi

        test      edi, edi
        je        .break
        
        call      convert_hex_digit_to_num ; converting

        call      mul_16_number
        call      add_small_number

        inc       esi
        inc       ebx
        jmp       .continue
        .break:

        pop       ecx
        pop       ebx
        pop       edi
        pop       esi

; ebx - first string char
; result - sing field is xored with 1, ebx++ (if needed)
get_input_sign:
		movzx     eax, byte [ebx]
        cmp	      al, '-'
        jne       isPositive3
        xor       byte [sign], 1
        inc       ebx
        isPositive3:
        ret

; number - input
; eax - output (0 - positive or 0, 1 - negative)
get_arithmetic_sign:
	    mov       eax, [number + 12]
	    shr       eax, 31
	    ret
	    

; number becames abs(number)
number_abs:
		push eax
		call      get_arithmetic_sign
		cmp       eax, 1
		jne       InvertNotNeeded

		not      dword [number]
		not      dword [number + 4]
		not      dword [number + 8]
		not      dword [number + 12]

		mov      eax, 1
		call     add_small_number
		
		InvertNotNeeded:
		pop eax
		ret

; edi - first format char
parse_flags:
		push edi
		push eax
		push ebx
		push esi
		push ecx
		push edx ; last symbol - number?

		mov       ebx, edi
		xor       eax, eax
		xor       esi, esi
		mov       ecx, 10
		xor       edx, edx
		
		start_loop:
		movzx     edi, byte [ebx]          ; loading one char to edi
		test      edi, edi
        je        break_loop

        movzx     eax, byte [ebx]
        
        cmp	      al, '-'
        jne       isNotMinus
        mov       byte [minus_flag], 1
        mov       edx, 0
        isNotMinus:

        cmp	      al, '+'
        jne       isNotPlus
        mov       byte [plus_flag], 1
        mov       edx, 0
        isNotPlus:

        cmp	      al, ' '
        jne       isNotSpace
        mov       byte [space_flag], 1
        mov       edx, 0
        isNotSpace:

        cmp	      al, '0'
        jne       isNotZero
        cmp       edx, 0
        jne       isWidth
        mov       byte [zero_flag], 1
        mov       edx, 0
        isNotZero:

        cmp	      al, '1'
        jl        isNotWidth
        cmp	      al, '9'
        jg        isNotWidth
        isWidth:
        sub       eax, '0'

        push      eax
        push      esi
        pop       eax
        pop       esi
        mul       ecx
        add       eax, esi
        push      eax
        push      esi
        pop       eax
        pop       esi

        mov       [width_field], esi
        mov       edx, 1
        isNotWidth:

        inc ebx
		jmp start_loop
		break_loop:

		cmp byte [plus_flag], 1
		jne NoPlusFlag
		mov byte [space_flag], 0
		NoPlusFlag:

		cmp byte [minus_flag], 1
		jne NoMinusFlag
		mov byte [zero_flag], 0
		NoMinusFlag:

		call get_number_len
		mov      edi, eax
		cmp      eax, 0
		jne      notZeroLen
		mov      byte [sign], 0
		inc      edi
		notZeroLen:

        call     sign_needed

        add      eax, edi
		cmp eax, [width_field]

		jge gOrE
		sub [width_field], eax
		jmp endParse
		gOrE:
		mov byte [width_field], 0
		endParse:

		pop edx
		pop ecx
		pop esi
		pop ebx
		pop eax
		pop edi
	    ret


; function muls number on 16
; number - big number
; result - number
mul_16_number:
        push      eax
        push    edi

        mov       eax, [number + 12]
        mov       edi, [number + 8]
        shld      eax, edi, 4
        mov      [number + 12], eax
        mov      [number + 8], edi

        mov       eax, [number + 8]
        mov       edi, [number + 4]
        shld      eax, edi, 4
        mov      [number + 8], eax
        mov      [number + 4], edi

        mov       eax, [number + 4]
        mov       edi, [number]
        shld      eax, edi, 4
        mov      [number + 4], eax
        mov      [number], edi

        mov       eax, [number]
        mov       edi, 0
        shld      eax, edi, 4
        mov      [number], eax
    
  		pop       edi                                                                               
        pop       eax
        ret


; function adds small number to big
; eax    - small number
; number - big number
; result - number
add_small_number:
        push      eax

        add       [number], eax
        adc      dword [number + 4], 0
        adc      dword [number + 8], 0
        adc      dword [number + 12], 0
                                                                                                                                                                                                                                                                                                                                                                                                                                                          
        pop       eax
        ret

; edi -- char  hex_digit
; eax -- result: int dec_number
convert_hex_digit_to_num:
        push      edi

        mov       eax, edi
        jmp       check_for_digit

        end_of_converting:

        pop       edi
        ret

check_for_digit:
        cmp       al, '0'     ; handle 0-9
        jl       check_for_upper
        cmp       al, '9'
        jg       check_for_upper
        sub       al, '0'     ; convert to numeric value
        jmp       end_of_converting


check_for_upper:
        cmp       al, 'A'     ; handle A-F
        jl       check_for_lower
        cmp       al, 'F'
        jg       check_for_lower
        sub       al, 'A'-10  ; convert to numeric value
        jmp       end_of_converting


check_for_lower:
        sub       al, 'a'-10
        jmp       end_of_converting

; number - number to print
; esi - pointer to out
print_number:
		push      eax
        push      dword [number]
        push      dword [number+4]
        push      dword [number+8]
        push      dword [number+12]

        call      get_number_len
        cmp       eax, 0
        jne       isNotZ
        inc       eax
        isNotZ:

        start_printing:
        cmp       eax, 0
        je        stop_printing
        dec       eax
        

        print_digit:
        call      decimal_to_out
        jmp       start_printing
        stop_printing:

        pop      dword [number+12]
        pop      dword [number+8]
        pop      dword [number+4]
        pop      dword [number]
        pop      eax
        ret


; esi - pointer to out
; eax - int decimal
decimal_to_out:
        ;push      esi
        push      eax
        push      ecx


        xor       edx, edx
        .continue:
        mov       ecx, 10

        mov       eax, [number+12]
        div       ecx
        mov       [number+12], eax

        mov       eax, [number+8]
        div       ecx
        mov       [number+8], eax

        mov       eax, [number+4]
        div       ecx
        mov       [number+4], eax

        mov       eax, [number]
        div       ecx
        mov       [number], eax

        ;Циферка в edx
        mov       eax, edx
        add       eax, '0'
        mov [esi], eax
        inc esi

        pop       ecx
        pop       eax
        ;pop       esi
        ret

; esi - pointer to out
; eax - int decimal
decimal_to_out2:
        push      eax
        push      ecx


        xor       edx, edx
        .continue2:
        mov       ecx, 10

        mov       eax, [number+12]
        div       ecx
        mov       [number+12], eax

        mov       eax, [number+8]
        div       ecx
        mov       [number+8], eax

        mov       eax, [number+4]
        div       ecx
        mov       [number+4], eax

        mov       eax, [number]
        div       ecx
        mov       [number], eax

        ;Циферка в edx
        mov       eax, edx
        add       eax, '0'

        pop       ecx
        pop       eax
        ret

; esi - out
reverse:
        push ebp           ; prologue
        push esi
        push eax
        push edx

        mov ebp, esp       
        mov eax, esi   ; eax <- points to string
        mov edx, eax
		
		look_for_last:
        mov ebx, [edx]      ; put char from edx in ebx
        inc edx
        test ebx, ebx        
        jnz look_for_last  ; if char != 0 loop
        sub edx, 2         ; found last
		
		swap:                      ; eax = first, edx = last (characters in string)
        cmp eax, edx       
        jg end             ; if eax > edx reverse is done
        mov cl, [eax]      ; put char from eax in edi
        mov ch, [edx]      ; put char from edx in ebx
        mov [edx], cl      ; put edi in edx
        mov [eax], ch      ; put ebx in eax
        inc eax
        dec edx
        jmp swap            
		
		end:
        
        pop edx
        pop eax
        pop esi
        pop ebp            ; epilogue
        ret

; check if esi - empty and add '0'
check_for_zero:
	push eax
	push esi

	mov eax, [esi]
	cmp al, '+'
	jne firstNotPlus
	inc esi
	firstNotPlus:

	mov eax, [esi]
	test eax, eax
	jne exit_cfz
	mov eax, '0'
	mov [esi], eax	

	exit_cfz:
	pop esi
	pop eax
	ret