<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>

      <div class="container my-3 py-3 px-3">
            <div class="p-3">
                  <h2 style="text-align: center;">인재 목록</h2>
            </div>
            <div class="d-flex justify-content-end mb-2">
                  <select class="form-select" style="width: 123px;">
                        <option>관련도순</option>
                        <option> 연차순</option>
                        <option> 날짜순</option>
                  </select>
            </div>
            <c:choose>
                  <c:when test="${principal.role eq 'company'}">
                  <h2 style="text-align: center;">최근 공고에 대한 추천 유저입니다.</h2>
                  <div class="my-border-color-default p-3">
                        <!-- 카드 들어갈 곳 -->
                        <div class="row gx-3">
                              <c:forEach items="${recommendEmployeeList}" var="recommendEmployee">
                              <div class="col-md-3 py-2">
                                    <div id="employee${recommendEmployee.id}" onmouseenter="mouseEnterImages(this)"
                                          onmouseleave="mouseLeaveImages(this)" class="card col-lg-12">
                                          <a href="/employee/${recommendEmployee.id}" class="no_under_line_link">
                                                <img class="card-img-top" style="height: 100px;" src="${recommendEmployee.profile == null ? " /images/newjeans.jpg" :
                                                recommendEmployee.profile}"
                                                      alt="Card image">
                                                <div class="card-body">
                                                      <div class="my-text-ellipsis">
                                                            <h5>${recommendEmployee.realName}</h5>
                                                      </div>
                                                      <div class="my-text-ellipsis">
                                                            <c:choose>
                                                            <c:when test="${recommendEmployee.career == 0}">
                                                            신입
                                                            </c:when>
                                                            <c:otherwise>
                                                            ${recommendEmployee.career} 년차
                                                            </c:otherwise>
                                                            </c:choose>
                                                      </div>
                                                      <div class="my-text-ellipsis">
                                                            ${recommendEmployee.title}
                                                      </div>
                                                </div>
                                          </a>
                                          <div class="card-footer d-flex justify-content-between">
                                                <div>${recommendEmployee.createdAtToString}</div>
                                                <div><i id="heart" class="fa-regular fa-heart my-xl my-cursor fa-lg"></i>
                                                </div>
                                          </div>
                                    </div>
                              </div>
                              </c:forEach>
                              <!-- 반복문 종료 -->
                        </div>
                  </div>
                  <div class="p-3">
                  <div class="row">
                  <c:forEach items="${allEmployeeList}" var="employee">
                        <div class="col-md-3 py-2">
                              <div id="employee${employee.id}" onmouseenter="mouseEnterImages(this)"
                                    onmouseleave="mouseLeaveImages(this)" class="card col-lg-12">
                                    <a href="/employee/${employee.id}" class="no_under_line_link">
                                          <img class="card-img-top" style="height: 100px;" src="${user.profile == null ? " /images/newjeans.jpg" :
                                            employee.profile}"
                                                alt="Card image">
                                          <div class="card-body">
                                                <div class="my-text-ellipsis">
                                                      <h5>${employee.realName}</h5>
                                                </div>
                                                <div class="my-text-ellipsis">
                                                      <c:choose>
                                                      <c:when test="${employee.career == 0}">
                                                      신입
                                                      </c:when>
                                                      <c:otherwise>
                                                      ${employee.career} 년차
                                                      </c:otherwise>
                                                      </c:choose>
                                                </div>
                                                <div class="my-text-ellipsis">
                                                      ${employee.title}
                                                </div>
                                          </div>
                                    </a>
                                    <div class="card-footer d-flex justify-content-between">
                                          <div>${employee.createdAtToString}</div>
                                          <div><i id="heart" class="fa-regular fa-heart my-xl my-cursor fa-lg"></i>
                                          </div>
                                    </div>
                              </div>
                        </div>
                        </c:forEach>
                  </div>
            </div>                                 
                  </c:when>
                  <c:otherwise>
                  <div class="p-3">
                        <div class="row">
                        <c:forEach items="${allEmployeeList}" var="employee">
                              <div class="col-md-3 py-2">
                                    <div id="employee${employee.id}" onmouseenter="mouseEnterImages(this)"
                                          onmouseleave="mouseLeaveImages(this)" class="card col-lg-12">
                                          <a href="/employee/${employee.id}" class="no_under_line_link">
                                                <img class="card-img-top" style="height: 100px;" src="${user.profile == null ? " /images/newjeans.jpg" :
                                                employee.profile}"
                                                      alt="Card image">
                                                <div class="card-body">
                                                      <div class="my-text-ellipsis">
                                                            <h5>${employee.realName}</h5>
                                                      </div>
                                                      <div class="my-text-ellipsis">
                                                            <c:choose>
                                                            <c:when test="${employee.career == 0}">
                                                            신입
                                                            </c:when>
                                                            <c:otherwise>
                                                            ${employee.career} 년차
                                                            </c:otherwise>
                                                            </c:choose>
                                                      </div>
                                                      <div class="my-text-ellipsis">
                                                            ${employee.title}
                                                      </div>
                                                </div>
                                          </a>
                                          <div class="card-footer d-flex justify-content-between">
                                                <div>${employee.createdAtToString}</div>
                                                <div><i id="heart" class="fa-regular fa-heart my-xl my-cursor fa-lg"></i>
                                                </div>
                                          </div>
                                    </div>
                              </div>
                        </c:forEach>
                        </div>
                  </div>
                  </c:otherwise>
            </c:choose>
            
      </div>
      <script>
            function mouseEnterImages(e) {
                  let id = e.getAttribute('id');
                  $("#" + id).addClass("border border-primary");
            }

            function mouseLeaveImages(e) {
                  let id = e.getAttribute('id');
                  $("#" + id).removeClass("border border-primary");
            }
      </script>

 <%@ include file="../layout/footer.jsp" %>