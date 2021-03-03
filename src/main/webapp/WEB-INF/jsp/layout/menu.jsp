<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <div class="sidebar">
            <div class="logo">
                <a href="#"><img src="/images/mindone.png" alt="logo"></a>
                <div class="about"><span>BA2</span> 박이정</div><!-- 화면width 줄어들면 보인다 -->
            </div>
            <ul>
                <li><a href="#">인벤토리</a>
                    <ul class="depth">
                        <li class="on"><a href="/asst/list"><i class="fas fa-book"></i>수원자산</a></li>
                        <!-- <li><a href="/domain/page"><i class="far fa-square"></i>도메인</a></li>
                        <li><a href="/word/page"><i class="fas fa-cube"></i>단어</a></li>
                        <li><a href="/term/page"><i class="fas fa-cubes"></i>용어</a></li> -->
                    </ul>
                </li>
                <li><a href="#">기본정보</a>
                    <ul class="depth">
                        <li><a href="/prjct/page"><i class="fas fa-object-group"></i>프로젝트</a></li>
                        <li><a href="/code/page"><i class="fab fa-cuttlefish"></i>코드</a></li>
                        <li><a href="/cdgrp/page"><i class="fas fa-copyright"></i>코드그룹</a></li>
                    </ul>
                </li>
                <li><a href="#">사용자</a>
                    <ul class="depth">
                        <li><a href="/user/page"><i class="fas fa-user"></i>직원정보</a></li>
                        <li><a href="/dept/page"><i class="fas fa-address-book"></i>부서정보</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="m_page"><!-- 화면width 줄어들면 보인다 -->
			    <li><a href="/myinfo/page">내정보수정</a></li>
			    <li><a href="/login/logout">로그아웃</a></li>
		    </ul>
        </div><!--//sidebar-->
