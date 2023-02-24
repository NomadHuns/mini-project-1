<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
 <div class="container my-3">
        <main class="mt-5 pt-5">
            <div class="container-fluid px-4">
                <h2 class="mt-4">문의함 목록보기</h2>
                </br>

                <div class="card mb-4">
                    <div class="card-header">
                        <a class="btn btn-primary float-end" href="">
                            <i class="fas fa-edit"></i> 글 작성
                        </a>
                    </div>
                    <div class="card-body">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th>글번호</th>
                                    <th>제목</th>
                                    <th>작성자</th>
                                    <th>조회수</th>
                                    <th>작성일</th>
                                </tr>
                            </thead>
                            <tbody>

                                <thead>
                                    <tr>
                                        <th>1</th>
                                        <th>문의해요</th>
                                        <th>김지윤</th>
                                        <th class="px-3">3</th>
                                        <th>2023-02-23</th>
                                    </tr>
                                </thead>
                            <tbody>
                                <tr>
                                    <th>1</th>
                                    <th>문의해요</th>
                                    <th>김지윤</th>
                                    <th class="px-3">3</th>
                                    <th>2023-02-23</th>
                                </tr>
                                <tr>
                                    <th>1</th>
                                    <th>문의해요</th>
                                    <th>김지윤</th>
                                    <th class="px-3">3</th>
                                    <th>2023-02-23</th>
                                </tr>
                                <tr>
                                    <th>1</th>
                                    <th>문의해요</th>
                                    <th>김지윤</th>
                                    <th class="px-3">3</th>
                                    <th>2023-02-23</th>
                                </tr>
                                <tr>
                                    <th>1</th>
                                    <th>문의해요</th>
                                    <th>김지윤</th>
                                    <th class="px-3">3</th>
                                    <th>2023-02-23</th>
                                </tr>
                                <tr>
                                    <th>1</th>
                                    <th>문의해요</th>
                                    <th>김지윤</th>
                                    <th class="px-3">3</th>
                                    <th>2023-02-23</th>
                                </tr>
                                <tr>
                                    <th>1</th>
                                    <th>문의해요</th>
                                    <th>김지윤</th>
                                    <th class="px-3">3</th>
                                    <th>2023-02-23</th>
                                </tr>

                            </tbody>
                        
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            </form>
    </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-sm-4 b-3 p-3">
            </div>
            <div class="col-sm-4 b-3 p-3">
                <div class="row">
                    <div class="col-sm-3 b-3 p-3">
                    </div>
                    <div class="col-sm-4">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="#">이전</a></li>
                        </ul>
                    </div>
                    <div class="col-sm-4">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="#">다음</a></li>
                        </ul>
                    </div>
                    <div class="col-sm-1 b-3 p-3">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        $('.summernote').summernote({
            tabsize: 2,
            height: 500
        });
    </script>
        <%@ include file="../layout/footer.jsp" %>