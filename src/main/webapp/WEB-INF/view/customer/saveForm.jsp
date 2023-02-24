<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
<div class="container my-3">
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <h2 class="text-center">문의하기</h2>
                <p></p>
                <div class="my-border-color-default px-3 py-3" style="width:100%; ">
                    <div class="table table-responsive">
                        <table class="table table-striped">
                            <tr>
                                <td>문의종류</td>
                                <td>
                                    <select class="form-select" aria-label="Default select example">
                                        <option selected>선택하세요</option>
                                        <option value="1">입사지원 문의</option>
                                        <option value="2">이력서 문의</option>
                                        <option value="3">제안 사항</option>
                                        <option value="4">검색 문의</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>제목</td>
                                <td><input type="title" class="form-control" name="title"></td>
                            </tr>
                            <tr>
                                <td>내용</td>
                                <td><textarea rows="10" cols="50" name="content" class="form-control"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="text-center">
                                    <div class="" my-3 mt-3">
                                        <button type="reset"
                                            class="btn btn-primary my-button-color-btn btn-primary">완료</button>
                                        <button type="submit"
                                            class="btn btn-secondary my-button-color-btn btn-secondary">취소</button>
                                        <button type="reset"
                                            class="btn btn-success my-button-color-btn btn-success">전체글보기</button>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
        <%@ include file="../layout/footer.jsp" %>