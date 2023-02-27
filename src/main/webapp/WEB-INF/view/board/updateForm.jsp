<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>


        <form action="/board/update/${boardDetail.id}" method="post">

            <div class="container py-3 px-3">
                <div class="justify-content-center d-flex my-3 py-3">
                    <h2>
                        기업 공고 수정
                    </h2>
                </div>

                <div class="my-border-color-default px-3 py-3" style="width: 100%; ">

                    <div class="px-3">
                        <div class="title column py-3">
                            <input id="title" name="title" class="title form-control my-border-color-default"
                                type="text" value="${boardDetail.title}">
                        </div>
                        <div class="content column py-4 ps-3  my-border-color-default" style="display: grid;
                grid-template-columns: 4fr 6fr;">
                            <div class="ms-4" style="display: grid; grid-template-columns: 2.7fr 7.3fr;">
                                <div>
                                    <div class="mb-3">경력</div>
                                    <div class="mb-3">채용분야</div>
                                    <div>마감일자</div>
                                </div>
                                <div>
                                    <div class="mb-3">
                                        <select id="career" name="careerString" class="career my-border-color-default"
                                            style="width: 50%;" value="${boardDetail.careerString}">
                                            <option>신입</option>
                                            <option>1년이상 ~ 3년미만</option>
                                            <option>3년이상 ~ 5년미만</option>
                                            <option>6년이상</option>
                                            <option>경력무관</option>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <select class="field my-border-color-default" style="width: 50%;">
                                            <option>채용분야</option>
                                            <option>백엔드</option>
                                            <option>프론트엔드</option>
                                            <option>김지윤</option>
                                            <option>김지윤</option>
                                        </select>
                                    </div>
                                    <div>
                                        <div class="Deadline">
                                            <input type="date" class="form-control text-primary my-border-color-default"
                                                style="width:60%" id="Deadline">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style="display: grid;
                    grid-template-columns: 1fr 8fr 2fr;">
                                <div></div>
                                <div>
                                    <div class="mb-2"></div>
                                    <div class="mb-3">
                                        관심분야
                                    </div>
                                    <div class="mb-2">
                                        <div class="form-check form-check-inline" style="width: 19%">
                                            <input class="form-check-input" type="checkbox" id="java"
                                                value="option1">
                                            <label class="form-check-label" for="inlineCheckbox1">Java</label>
                                        </div>
                                        <div class="form-check form-check-inline" style="width: 19%">
                                            <input class="form-check-input" type="checkbox" id="cLang"
                                                value="option2">
                                            <label class="form-check-label" for="inlineCheckbox2">C#</label>
                                        </div>
                                        <div class="form-check form-check-inline" style="width: 19%">
                                            <input class="form-check-input" type="checkbox" id="python"
                                                value="option1">
                                            <label class="form-check-label" for="inlineCheckbox1">Python</label>
                                        </div>
                                        <div class="form-check form-check-inline" style="width: 19%">
                                            <input class="form-check-input" type="checkbox" id="php"
                                                value="option2">
                                            <label class="form-check-label" for="inlineCheckbox2">PHP</label>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="form-check form-check-inline" style="width: 19%">
                                            <input class="form-check-input" type="checkbox" id="jsc"
                                                value="option1">
                                            <label class="form-check-label" for="inlineCheckbox1">JS</label>
                                        </div>
                                        <div class="form-check form-check-inline" style="width: 19%">
                                            <input class="form-check-input" type="checkbox" id="ruby"
                                                value="option2">
                                            <label class="form-check-label" for="inlineCheckbox2">Ruby</label>
                                        </div>
                                        <div class="form-check form-check-inline" style="width: 19%">
                                            <input class="form-check-input" type="checkbox" id="assemblyLang"
                                                value="option1">
                                            <label class="form-check-label" for="inlineCheckbox1">Assembly</label>
                                        </div>
                                        <div class="form-check form-check-inline" style="width: 19%">
                                            <input class="form-check-input" type="checkbox" id="sqlLang"
                                                value="option2">
                                            <label class="form-check-label" for="inlineCheckbox2">SQL</label>
                                        </div>
                                    </div>
                                    <div>
                                    </div>
                                </div>
                                <div></div>
                            </div>
                        </div>
                        <form class="mb-1 mt-4">
                            <div class="form-group">
                                <textarea class="form-control summernote" rows="5" name="content"
                                    id="content">${boardDetail.content}</textarea>
                        </form>

                    </div>
                </div>
                <div class="d-flex justify-content-center">
                    <div class="btn-group m-4" role="group" aria-label="Basic example ">
                        <button type="submit" class="btn btn-primary" id="btnSave">수정</button>
                    </div>
                    <div class="btn-group m-4" role="group" aria-label="Basic example ">
                        <button id="btnCancel" type="button" class="btn btn-secondary"
                            onclick="location.href=`/board/${boardDetail.id}`;">취소</button>
                    </div>



                </div>
            </div>
        </form>
        <script>
            $('#career').val('${boardDetail.careerString}').prop("selected",true);

            $('.summernote').summernote({
                tabsize: 2,
                height: 500
            });
        </script>


        <%@ include file="../layout/footer.jsp" %>