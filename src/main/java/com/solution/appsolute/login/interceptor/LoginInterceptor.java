package com.solution.appsolute.login.interceptor;



//@Component
//public class LoginInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpSession session = request.getSession();
//        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
//        System.out.println("나 로그인 인터셉터 =====================" + authInfo);
//        if(ObjectUtils.isEmpty(authInfo)){
//            response.sendRedirect("/");
//            return false;
//        }else {
//            session.setMaxInactiveInterval(7200);
//            return true;
//        }
//    }
//@Override
//public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//    HttpSession session = request.getSession(false);
//    if (session != null) {
//        Object authInfo = session.getAttribute("authInfo");
//        if (authInfo != null) {
//            return true;
//        }
//    }
//    response.sendRedirect(request.getContextPath() + "/");
//    return false;
//}
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
//}
