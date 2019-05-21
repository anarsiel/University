                section         .text

                global          _start
_start:

                sub             rsp, 2 * 128 * 8
                lea             rdi, [rsp + 128 * 8]
                mov             rcx, 128
                call            read_long
                mov             rdi, rsp
                call            read_long
                lea             rsi, [rsp + 128 * 8]

                call            swap_rdi_rsi

                call            sub_long_long

                call            write_long

                mov             al, 0x0a
                call            write_char

                jmp             exit

; adds two long number
;    rdi -- address of summand #1 (long number)
;    rsi -- address of summand #2 (long number)
;    rcx -- length of long numbers in qwords
; result:
;    sum is written to rdi

swap_rdi_rsi:
                push            rsi
                push            rdi

                pop             rsi
                pop             rdi
                ret

sub_long_long:
                push            rdi
                push            rsi
                push            rcx

                clc
.loop:
                mov             rax, [rsi]
                lea             rsi, [rsi + 8]
                sbb             [rdi], rax
                lea             rdi, [rdi + 8]
                dec             rcx
                jnz             .loop

                pop             rcx
                pop             rsi
                pop             rdi
                ret

; adds 64-bit number to long number
;    rdi -- address of summand #1 (long number)
;    rax -- summand #2 (64-bit unsigned)
;    rcx -- length of long number in qwords
; result:
;    sum is written to rdi
add_long_short:
                push            rdi
                push            rcx
                push            rdx

                xor             rdx, rdx
.loop:
                add             [rdi], rax
                adc             rdx, 0 ; rdx += кэрри флаг
                mov             rax, rdx
                xor             rdx, rdx
                add             rdi, 8
                dec             rcx
                jnz             .loop

                pop             rdx
                pop             rcx
                pop             rdi
                ret

; multiplies long number by a short
;    rdi -- address of multiplier #1 (long number)
;    rbx -- multiplier #2 (64-bit unsigned)
;    rcx -- length of long number in qwords
; result:
;    product is written to rdi
mul_long_short:
                push            rax
                push            rdi
                push            rcx

                xor             rsi, rsi
.loop:
                mov             rax, [rdi] ; мы взяли первые четыре байта числа
                mul             rbx ; если у нас раикс переполнился то мы старшие биты записываются в рсай
                add             rax, rsi ; прибавляем рсай с предыдущего шага
                adc             rdx, 0 ; адд с флагом переноса
                mov             [rdi], rax
                add             rdi, 8
                mov             rsi, rdx
                dec             rcx
                jnz             .loop

                pop             rcx
                pop             rdi
                pop             rax
                ret

; divides long number by a short
;    rdi -- address of dividend (long number)
;    rbx -- divisor (64-bit unsigned)
;    rcx -- length of long number in qwords
; result:
;    quotient is written to rdi
;    rdx -- remainder
div_long_short:
                push            rdi
                push            rax
                push            rcx

                lea             rdi, [rdi + 8 * rcx - 8] ;
                xor             rdx, rdx

.loop:
                mov             rax, [rdi]
                div             rbx ; мы тут делим на самом делим число rdxrax. т.е. остаток от деление используется на следующей итерации
                mov             [rdi], rax
                sub             rdi, 8
                dec             rcx
                jnz             .loop

                pop             rcx
                pop             rax
                pop             rdi
                ret

; assigns a zero to long number
;    rdi -- argument (long number)
;    rcx -- length of long number in qwords
set_zero:
                push            rax
                push            rdi ; присваивает заачению по адресу raxб поэтому он двигаетсяаа
                push            rcx ; становится нулем т.к. счетчик

                xor             rax, rax
                rep stosq ; заполняет rcx раз в поле rdi rax со сдвигом в один ворд

                pop             rcx
                pop             rdi
                pop             rax
                ret

; checks if a long number is a zero
;    rdi -- argument (long number)
;    rcx -- length of long number in qwords
; result:
;    ZF=1 if zero
is_zero:
                push            rax
                push            rdi ; указатель на младший разряд выводимого числа
                push            rcx

                xor             rax, rax
                rep scasq ; сравнить rcx раз поле rdi с rax со сдвигом в один ворд

                pop             rcx
                pop             rdi
                pop             rax
                ret

; read long number from stdin
;    rdi -- location for output (long number)
;    rcx -- length of long number in qwords
read_long:
                push            rcx
                push            rdi

                call            set_zero
.loop:
                call            read_char
              ; xor              rax, rax ; не нужно
                js              exit
                cmp             rax, 0x0a
                je              .done
                cmp             rax, '0'
                jb              .invalid_char
                cmp             rax, '9'
                ja              .invalid_char

                sub             rax, '0'
                mov             rbx, 10
                call            mul_long_short
                call            add_long_short
                jmp             .loop

.done:
                pop             rdi
                pop             rcx
                ret

.invalid_char:
                mov             rsi, invalid_char_msg
                mov             rdx, invalid_char_msg_size
                call            print_string
                call            write_char
                mov             al, 0x0a
                call            write_char

.skip_loop:
                call            read_char
                ;or              rax, rax
                js              exit
                cmp             rax, 0x0a
                je              exit
                jmp             .skip_loop

; write long number to stdout
;    rdi -- argument (long number)
;    rcx -- length of long number in qwords
write_long:
                push            rax
                push            rcx

                mov             rax, 20
                mul             rcx
                mov             rbp, rsp
                sub             rsp, rax

                mov             rsi, rbp

.loop:
                mov             rbx, 10
                call            div_long_short
                add             rdx, '0'; в rdx лежит младшая цифра числа и мы делаем из нее чар
                dec             rsi
                mov             [rsi], dl ; в рсай поцифренно записываем число
                call            is_zero
                jnz             .loop

                mov             rdx, rbp ; в рбп начало число
                sub             rdx, rsi ; в рсай конец
                call            print_string

                mov             rsp, rbp
                pop             rcx
                pop             rax
                ret

; read one char from stdin
; result:
;    rax == -1 if error occurs
;    rax \in [0; 255] if OK
read_char:
                push            rcx
                push            rdi

                sub             rsp, 1
                xor             rax, rax ; тип сискол считывание
                xor             rdi, rdi ; считываем из стдин
                mov             rsi, rsp ; сказали считать в рсп
                mov             rdx, 1 ; сколько считать
                syscall ; поместили в рсай куда считать. поместили мы адрес рсп

                cmp             rax, 1 ; возвращает количество аргументов считанных
                jne             .error
                xor             rax, rax
                mov             al, [rsp] ; самая маленькая самая маленькая часть регистра Rax, 1 байт
                add             rsp, 1 ; двигаем указатель памяти на 1 байт

                pop             rdi
                pop             rcx
                ret
.error:
                mov             rax, -1
                add             rsp, 1 ; двигаем указатель на место куда считать чтобы в сл раз не потерять что считали
                pop             rdi
                pop             rcx
                ret

; write one char to stdout, errors are ignored
;    al -- char
write_char:
                sub             rsp, 1 ; вычли там чет даня про стек даня говорил
                mov             [rsp], al

                mov             rax, 1
                mov             rdi, 1
                mov             rsi, rsp
                mov             rdx, 1 ; сколько выводить
                syscall
                add             rsp, 1
                ret

exit:
                mov             rax, 60
                xor             rdi, rdi
                syscall

; print string to stdout
;    rsi -- string
;    rdx -- size
print_string:
                push            rax

                mov             rax, 1
                mov             rdi, 1
                syscall

                pop             rax
                ret


                section         .rodata
invalid_char_msg:
                db              "Invalid character: "
invalid_char_msg_size: equ             $ - invalid_char_msg