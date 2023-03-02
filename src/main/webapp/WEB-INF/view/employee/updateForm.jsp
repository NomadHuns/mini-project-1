<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>

        <div class="container my-3 py-3 px-3">
            <div class="justify-content-center d-flex py-3">
                <h2>회원 정보 수정</h2>
            </div>
            <div class="justify-content-center d-flex">
                <div class="my-border-color-default px-3 py-3" style="width: 50%; ">
                    <form action="/employee/update" method="post" enctype="multipart/form-data" onsubmit="return valid()">
                    <div class="row">
                        <div class="col-md-5">
                            <div class="mb-3">
                                <label for="exampleInputPassword" class="form-label">변경 비밀번호</label>
                                <input type="password" name="password" id="password" class="form-control"
                                    value="${employeeDto.password}" placeholder="Password" onchange="checkSamePassword()">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">비밀번호확인</label>
                                <input type="password" class="form-control" id="passwordCheck" placeholder="Confirm Password"
                                    onchange="checkSamePassword()">
                            </div>
                            <div id="passwordCheckAlert">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">이메일</label>
                                <input name="email" type="email" class="form-control" placeholder="Email"
                                    value="${employeeDto.email}">
                            </div>
                        </div>
                        <div class="col-md-7">
                            <div class="d-flex justify-content-center">
                                <div class="form-group d-flex justify-content-center my-thumbnail-color-default "
                                    style="height: 150px; width: 150px; align-items: center">

                                <img id="imagePreview" src="${principal.profile == null ? "/images/defaultProfile.png" : principal.profile}" alt="Current Photo"
                                                class="img-fluid" style="height:80px; ">
                                </div>
                            </div>

                            <div class="form-group mt-4 d-flex justify-content-center">
                                <input type="file" class="form-control" id="thumbnail" name="profile"
                                            onchange="chooseImage(this)" style="width: 250px; height: 35px; ">
                            </div>
                        </div>
                    </div>


                        <div class="mb-3">
                            <label class="form-label">학력</label>
                            <select name="education" type="text" class="form-select">
                                <option value="비공개">비공개</option>
                                <option value="고졸">고졸</option>
                                <option value="2~3년제 대졸">2~3년제 대졸</option>
                                <option value="4년제 대졸">4년제 대졸</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">실명</label>
                            <input name="realName" type="text" class="form-control" placeholder="realName"
                                value="${employeeDto.realName}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">주소</label>
                            <input name="address" type="text" class="form-control" placeholder="Address"
                                value="${employeeDto.address}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">상세주소</label>
                            <input name="detailAddress" type="text" class="form-control" placeholder="Detail Address"
                                value="${employeeDto.detailAddress}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">연락처</label>
                            <input name="tel" type="text" class="form-control" placeholder="Tel" value="${employeeDto.tel}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">경력 연차</label>
                            <input name="career" type="number" class="form-control" placeholder="Career Year" min="0"
                                value="${employeeDto.career}">
                        </div>
                        <label class="form-label">관심분야</label>
                        <div class="mb-5" style="display: grid;
                                        grid-template-columns: 1fr 8fr 2fr;">
                            <div></div>
                            <div>
                                <div class="mb-2"></div>
                                <div class="mb-3">
                                </div>
                                <div class="mb-2">
                                    <div class="form-check form-check-inline" style="width: 19%">
                                        <input class="form-check-input" type="checkbox" id="java">
                                        <label class="form-check-label" for="inlineCheckbox1">Java</label>
                                    </div>
                                    <div class="form-check form-check-inline" style="width: 19%">
                                        <input class="form-check-input" type="checkbox" id="cLang">
                                        <label class="form-check-label" for="inlineCheckbox2">C#</label>
                                    </div>
                                    <div class="form-check form-check-inline" style="width: 19%">
                                        <input class="form-check-input" type="checkbox" id="python">
                                        <label class="form-check-label" for="inlineCheckbox1">Python</label>
                                    </div>
                                    <div class="form-check form-check-inline" style="width: 19%">
                                        <input class="form-check-input" type="checkbox" id="php">
                                        <label class="form-check-label" for="inlineCheckbox2">PHP</label>
                                    </div>
                                </div>
                                <div>
                                    <div class="form-check form-check-inline" style="width: 19%">
                                        <input class="form-check-input" type="checkbox" id="jsc">
                                        <label class="form-check-label" for="inlineCheckbox1">JS</label>
                                    </div>
                                    <div class="form-check form-check-inline" style="width: 19%">
                                        <input class="form-check-input" type="checkbox" id="ruby">
                                        <label class="form-check-label" for="inlineCheckbox2">Ruby</label>
                                    </div>
                                    <div class="form-check form-check-inline" style="width: 19%">
                                        <input class="form-check-input" type="checkbox" id="assemblyLang">
                                        <label class="form-check-label" for="inlineCheckbox1">Assembly</label>
                                    </div>
                                    <div class="form-check form-check-inline" style="width: 19%">
                                        <input class="form-check-input" type="checkbox" id="sqlLang">
                                        <label class="form-check-label" for="inlineCheckbox2">SQL</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-primary my-button-color-default">정보 수정</button>
                        <a href="/resume/${user.id}">
                            <div class="btn btn-secondary ms-3">이력서 수정</div>
                        </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

                <script>
                    let checkPassword = false;
                    function valid() {
                        if (checkPassword == false) {
                            alert("비밀번호 확인란을 확인해주세요");
                            return false;
                        }
                        return true;
                    }
                    function checkSamePassword() {
                        let password = $("#password").val();
                        let passwordCheck = $("#passwordCheck").val();
                        if (password == passwordCheck) {
                            checkPassword = true;
                            $("#passwordCheckAlert").empty();
                            let el = `<div class="alert alert-success" id="passwordCheckAlert">
                                              <strong>비밀번호 확인 완료!</strong>
                                              </div>`;
                            $("#passwordCheckAlert").append(el);
                        } else {
                            checkPassword = false;
                            $("#passwordCheckAlert").empty();
                            let el = `<div class="alert alert-danger">
                                              <strong>비밀번호가 다릅니다!</strong>
                                              </div>`;
                            $("#passwordCheckAlert").append(el);
                        }
                    }
                </script>
                <script>
                    function chooseImage(obj) {
                    // console.log(obj);
                    // console.log(obj.files);
                    let f = obj.files[0];

                    if (!f.type.match("image.*")) {
                        alert("이미지를 등록해야 합니다.");
                        $("#thumbnail").val("");
                        return;
                    }

                    let reader = new FileReader();
                    reader.readAsDataURL(f);

                    reader.onload = function (e) {
                            console.log(e);
                            console.log(e.target.result);
                            $("#imagePreview").attr("src", e.target.result);
                        }
                    }
                </script>

        <%@ include file="../layout/footer.jsp" %>