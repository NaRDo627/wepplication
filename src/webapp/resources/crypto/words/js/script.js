var temp;
var cipherText;
var origText;

function input_Encrypt(){
    var input = document.getElementById("BOARD_CONTENT_ENC").value;
    temp = input;
}

function input_Decrypt(){
    var input = document.getElementById("BOARD_CONTENT_DEC").value;
    temp = input;
}

function output_Encrypt(){
    input_Encrypt();
    //문장을 암호화
    cipherText = Encrypt(temp, "passwd", 128);
    document.getElementById("ENCRYPT_RESULT").value = cipherText;
}

function output_Decrypt(){
    input_Decrypt();
    //암호화된 문장을 복호화
    origText = Decrypt(temp, "passwd", 128);
    document.getElementById("DECRYPT_RESULT").value = origText;
}