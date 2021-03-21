package foo.study.url.controller;


import foo.study.url.domain.ShortUrlRequestDto;
import foo.study.url.domain.Url;
import foo.study.url.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("api/short-url")
public class Controller {

    private final static String baseDomain = "http://localhost:8080/";
    private final static char[] digits = {
            '0' , '1' , '2' , '3' , '4' , '5' ,
            '6' , '7' , '8' , '9' ,
            'a' , 'b' , 'c' , 'd' , 'e' , 'f' ,
            'g' , 'h' , 'i' , 'j' , 'k' , 'l' ,
            'm' , 'n' , 'o' , 'p' , 'q' , 'r' ,
            's' , 't' , 'u' , 'v' , 'w' , 'x' ,
            'y' , 'z' ,
            'A' , 'B' , 'C' , 'D' , 'E' , 'F' ,
            'G' , 'H' , 'I' , 'J' , 'K' , 'L' ,
            'M' , 'N' , 'O' , 'P' , 'Q' , 'R' ,
            'S' , 'T' , 'U' , 'V' , 'W' , 'X' ,
            'Y' , 'Z'
    };

    private Repository repository = Repository.getInstance();

    @PostMapping
    public ResponseEntity<?> saveBoard(@RequestBody ShortUrlRequestDto shortUrlRequestDto) throws UnsupportedEncodingException {

        String url = shortUrlRequestDto.getUrl();
        System.out.println("url = " + url);

        char[] chars = url.toCharArray();

        // 전송 받은 URL 한글자씩 10진법으로(아스키코드 -> 10진법) 변환해 전부 더해준다.
        long sumChars = sumChars(chars);
        System.out.println("sumChars = " + sumChars);

        Url savedUrl = repository.findById(sumChars);
        if (savedUrl != null) {
            return ResponseEntity.ok().body(baseDomain + savedUrl.getShortDomain());
        }

        String encodeToLong = encodeToLong(sumChars);
        System.out.println("encodeToLong = " + encodeToLong);
        Url newUrl = new Url(url, encodeToLong);
        repository.save(sumChars, newUrl);

        return ResponseEntity.ok().body(baseDomain + encodeToLong);
    }

    private long sumChars(char[] chars) {

        int sumDecimal = 0;
        for (char aChar : chars) {
            int decimal = (int) aChar;
            sumDecimal += decimal;
        }

        return sumDecimal;
    }

    private String encodeToLong(long value) {

        StringBuilder stringBuilder = new StringBuilder();

        do {
            int i = (int) (value % digits.length);
            stringBuilder.append(digits[i]);
            value /= digits.length;
        } while (value > 0);

        return stringBuilder.toString();
    }

}