<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
    
    <div class="container my-3 py-3 px-3">
        <div class="justify-content-center d-flex py-3">
            <h2>회원가입</h2>
        </div>
        <div class="justify-content-center d-flex">
            <nav class="navbar navbar-expand-sm bg-light navbar-light" style="width: 50%;">
                <div class="container-fluid">
                    <ul class="nav nav-tabs">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">일반회원</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/company/joinForm">기업회원</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="justify-content-center d-flex">
            <div class="my-border-color-default px-3 py-3" style="width: 50%; ">
                <form>
                    <div class="mb-3">
                        <label class="form-label">아이디</label>
                        <input type="text" class="form-control" placeholder="Username">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">비밀번호</label>
                        <input type="password" class="form-control" placeholder="Password">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">비밀번호 확인</label>
                        <input type="password" class="form-control" placeholder="Confirm Password">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">이메일</label>
                        <input type="email" class="form-control" placeholder="Email">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">이용 약관</label>
                        <textarea class="form-control" rows="3">
Lorem ipsum dolor sit amet consectetur, adipisicing elit. Deleniti ea incidunt ratione voluptates enim. Voluptatem optio suscipit neque cumque omnis amet maiores possimus, recusandae laudantium pariatur eveniet nostrum dignissimos eligendi!
Lorem ipsum dolor sit, amet consectetur adipisicing elit. Reprehenderit laudantium provident laborum ipsam impedit, dolorum sit itaque sunt mollitia aspernatur, assumenda perferendis excepturi amet, corporis dicta molestias nostrum? Esse, perspiciatis! 
                    </textarea>
                    </div>
                    <div class="mb-3 form-check d-flex justify-content-end">
                        <input type="checkbox" class="form-check-input">
                        <label class="form-check-label ms-2">동의합니다</label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">개인 정보 수집 및 이용 약관</label>
                        <textarea class="form-control" rows="3">
Lorem ipsum dolor sit amet consectetur, adipisicing elit. Deleniti ea incidunt ratione voluptates enim. Voluptatem optio suscipit neque cumque omnis amet maiores possimus, recusandae laudantium pariatur eveniet nostrum dignissimos eligendi!
Lorem ipsum dolor sit, amet consectetur adipisicing elit. Reprehenderit laudantium provident laborum ipsam impedit, dolorum sit itaque sunt mollitia aspernatur, assumenda perferendis excepturi amet, corporis dicta molestias nostrum? Esse, perspiciatis! 
                        </textarea>
                    </div>
                    <div class="mb-3 form-check d-flex justify-content-end">
                        <input type="checkbox" class="form-check-input">
                        <label class="form-check-label ms-2">동의합니다</label>
                    </div>
                    <div class="d-flex justify-content-center">
                        <button type="submit" class="btn btn-primary my-button-color-default">회원가입</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
   
 <%@ include file="../layout/footer.jsp" %>