package shop.mtcoding.jobara.tech.model;

import java.security.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.jobara.tech.dto.TechReq.BoardTechReqDto;

@NoArgsConstructor
@Getter
@Setter
public class Tech {
    private Integer id;
    private Integer userId;
    private Integer boardId;
    private String java;
    private String cLang;
    private String python;
    private String php;
    private String jsc;
    private String ruby;
    private String assemblyLang;
    private String sqlLang;
    private Timestamp createdAt;

    public Tech(Integer boardId, BoardTechReqDto boardTechReqDto) {
        this.boardId = boardId;
        this.java = boardTechReqDto.getJava();
        this.cLang = boardTechReqDto.getCLang();
        this.python = boardTechReqDto.getPython();
        this.php = boardTechReqDto.getPhp();
        this.jsc = boardTechReqDto.getJsc();
        this.ruby = boardTechReqDto.getRuby();
        this.assemblyLang = boardTechReqDto.getAssemblyLang();
        this.sqlLang = boardTechReqDto.getSqlLang();
    }

    public void updateBoard(BoardTechReqDto boardTechReqDto) {
        this.java = boardTechReqDto.getJava();
        this.cLang = boardTechReqDto.getCLang();
        this.python = boardTechReqDto.getPython();
        this.php = boardTechReqDto.getPhp();
        this.jsc = boardTechReqDto.getJsc();
        this.ruby = boardTechReqDto.getRuby();
        this.assemblyLang = boardTechReqDto.getAssemblyLang();
        this.sqlLang = boardTechReqDto.getSqlLang();
    }

}
