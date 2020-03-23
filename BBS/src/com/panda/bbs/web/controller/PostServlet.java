package com.panda.bbs.web.controller;

import com.panda.bbs.domain.Post;
import com.panda.bbs.domain.PostDetail;
import com.panda.bbs.domain.User;
import com.panda.bbs.service.IPostService;
import com.panda.bbs.service.IUserService;
import com.panda.bbs.service.impl.PostServiceImpl;
import com.panda.bbs.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@WebServlet("/postServlet")
public class PostServlet extends HttpServlet {
    private IPostService postService = new PostServiceImpl();
    private IUserService userService = new UserServiceImpl();
    private int total = 0;
    private int pageSize = 10;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        System.out.println(method);
        if(method.equals("init")){
            // 首页初始化
            init(request,response);
        } else if(method.equals("getPostByPostId")){
            // 根据文章ID获取文章
            getPostByPostId(request, response);
        }else if(method.equals("getPostDetailByPostId")){
            // 获取文章详细信息（包括文章信息与作者信息）
            getPostDetailByPostId(request, response);
        }else if(method.equals("addPost")){
            // 新增文章
            addPost(request, response);
        }else if(method.equals("toEditPost")){
            // 前往编辑文章
            toEditPost(request, response);
        }else if(method.equals("editPost")){
            // 提交编辑文章
            editPost(request, response);
        }else if(method.equals("deletePost")){
            // 删除文章
            deletePost(request, response);
        }else if(method.equals("getAllPostDetail")){
            // 获取所有文章详细内容
            getAllPostDetail(request, response);
        }else if(method.equals("getPostDetailByDirAndOrder")){
            // 获取所有文章根据分类&排序方式&分页
            getPostDetailByDirAndOrder(request, response);
        }else if(method.equals("prevPage")){
            // 上一页
            prevPage(request, response);
        }else if(method.equals("nextPage")){
            // 下一页
            nextPage(request, response);
        }
    }

    // 根据文章ID获取文章
    protected void getPostByPostId(HttpServletRequest request, HttpServletResponse response){
        // 获取request域文章ID
        String postId = request.getParameter("postId");
        // 从数据库获取文章
        Post post = postService.getPostByPostId(postId);
        // 将文章放入request域
        request.setAttribute("post",post);
        try {
            request.getRequestDispatcher("/WEB-INF/pages/post/post.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 获取文章详细信息（包括文章信息与作者信息）
    protected void getPostDetailByPostId(HttpServletRequest request, HttpServletResponse response){
        // 获取request域文章ID
        String postId = request.getParameter("postId");
        // 从数据库获取文章
        Post post = postService.getPostByPostId(postId);
        // 阅读量+1
        post.setPostViewCount(post.getPostViewCount()+1);
        postService.modifyPost(post);
        // 根据作者ID获取作者信息
        User author = userService.getUserByUserId(post.getAuthorId());
        // 构造文章详细信息Bean
        PostDetail postDetail = new PostDetail(post,author);
        // 将文章放入request域
        request.setAttribute("postDetail",postDetail);

        try {
            request.getRequestDispatcher("/commentServlet?method=getCommentByPostId&postId="+postId).forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 新增文章
    protected void addPost(HttpServletRequest request, HttpServletResponse response){
        // 设置文章信息
        String postId = String.valueOf(UUID.randomUUID());
        // 作者即当前用户
        User author = (User) request.getSession().getAttribute("user");
        String dir = request.getParameter("class");
        String postTitle = request.getParameter("title");
        String postContent = request.getParameter("content");
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        // 构造一个文章对象
        Post post = new Post(postId, author.getUserId(), dir, postTitle, postContent, currentTime, currentTime, 0, 0, 0);
        // 将新文章插入数据库
        postService.addPost(post);
        // 构造文章详细内容对象
        PostDetail postDetail = new PostDetail(post, author);
        request.setAttribute("postDetail", postDetail);
        try {
            System.out.println("发布成功");
            request.getRequestDispatcher("/postServlet?method=getPostDetailByPostId&postId="+postId).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 前往编辑文章
    protected void toEditPost(HttpServletRequest request, HttpServletResponse response){
        // 获取request域文章ID
        String postId = request.getParameter("postId");
        // 从数据库获取文章
        Post post = postService.getPostByPostId(postId);
        // 将文章放到请求域
        request.setAttribute("post",post);
        try {
            request.getRequestDispatcher("/WEB-INF/pages/post/editPost.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 提交编辑文章
    protected void editPost(HttpServletRequest request, HttpServletResponse response){
        // 获取request域文章ID
        String postId = request.getParameter("postId");
        // 从数据库获取文章
        Post post = postService.getPostByPostId(postId);
        // 获取请求域中参数
        String dir = request.getParameter("class");
        String postTitle = request.getParameter("title");
        String postContent = request.getParameter("content");
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        // 更新post对象属性值
        post.setDir(dir);
        post.setPostTitle(postTitle);
        post.setPostContent(postContent);
        post.setUpdateTime(time);
        // 更新数据库中的post属性值值
        postService.modifyPost(post);
        // 获取文章作者，用于转发页面
        User author = userService.getUserByUserId(post.getAuthorId());
        // 构造文章详细信息对象
        PostDetail postDetail = new PostDetail(post,author);
        // 将文章详细信息放入请求域中
        request.setAttribute("postDetail",postDetail);
        try {
            request.getRequestDispatcher("/postServlet?method=getPostDetailByPostId&postId="+postId).forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 删除文章
    protected void deletePost(HttpServletRequest request, HttpServletResponse response){
        // 获取request域文章ID
        String postId = request.getParameter("postId");
        // 从数据库移除一篇文章
        postService.removePost(postId);
        request.getRequestDispatcher("/index.jsp");
    }

    // 获取所有文章详细内容
    protected void getAllPostDetail(HttpServletRequest request, HttpServletResponse response){
        // 获取所有文章
        List<Post> allPostList = postService.getAllPost();
        // 创建所有文章详细内容的集合
        List<PostDetail> allPostDetailList = new LinkedList<>();
        // 循环将文章+作者信息放入allPostDetailList集合
        for(Post post : allPostList){
            // 获取当前文章作者信息
            User author = userService.getUserByUserId(post.getAuthorId());
            // 构造文章详细内容对象
            PostDetail postDetail = new PostDetail(post,author);
            allPostDetailList.add(postDetail);
        }
        request.getSession().setAttribute("allPostDetailList",allPostDetailList);
        try {
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 初始化
    protected void init(HttpServletRequest request, HttpServletResponse response){
        // 初始化 分类&排序方式&文章总数&当前页
        String dir = ""; // 初始化分类为空
        String order = "按更新"; // 初始化排序为“按更新”排序
        int currentPage = 1; // 初始化当前页为第一页
        total = postService.getTotalByDirAndOrder(dir,order); // 文章总数
        // 将文章展示信息放入Session域中
        request.getSession().setAttribute("dir",dir);
        request.getSession().setAttribute("order",order);
        request.getSession().setAttribute("currentPage",currentPage);
        // 获取所有文章
        List<Post> allPostList = postService.getPostByDirAndOrder(dir,order,currentPage,pageSize);
        // 创建所有文章详细内容的集合
        List<PostDetail> allPostDetailList = new LinkedList<>();
        // 循环将文章+作者信息放入allPostDetailList集合
        for(Post post : allPostList){
            // 获取当前文章作者信息
            User author = userService.getUserByUserId(post.getAuthorId());
            // 构造文章详细内容对象
            PostDetail postDetail = new PostDetail(post,author);
            allPostDetailList.add(postDetail);
        }
        request.getSession().setAttribute("allPostDetailList",allPostDetailList);
        try {
            request.getRequestDispatcher("/WEB-INF/pages/post/index.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 根据分类&排序方式&当前页 展示文章列表
    protected void getPostDetailByDirAndOrder(HttpServletRequest request, HttpServletResponse response){
        // 分类&排序方式&文章总数
        String dir = request.getParameter("dir"); // 分类
        String order = request.getParameter("order"); // 排序
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        System.out.println(currentPage);
        // 将文章展示信息放入Session域中
        request.getSession().setAttribute("dir",dir);
        request.getSession().setAttribute("order",order);
        request.getSession().setAttribute("currentPage",currentPage);
        // 获取所有文章
        List<Post> allPostList = postService.getPostByDirAndOrder(dir,order,currentPage,pageSize);
        // 获取文章总数
        total = postService.getTotalByDirAndOrder(dir,order);
        // 创建所有文章详细内容的集合
        List<PostDetail> allPostDetailList = new LinkedList<>();
        // 循环将文章+作者信息放入allPostDetailList集合
        for(Post post : allPostList){
            // 获取当前文章作者信息
            User author = userService.getUserByUserId(post.getAuthorId());
            // 构造文章详细内容对象
            PostDetail postDetail = new PostDetail(post,author);
            allPostDetailList.add(postDetail);
        }
        request.getSession().setAttribute("allPostDetailList",allPostDetailList);
        try {
            request.getRequestDispatcher("/WEB-INF/pages/post/index.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 上一页
    protected void prevPage(HttpServletRequest request, HttpServletResponse response){
        int currentPage = (int) request.getSession().getAttribute("currentPage"); // 当前页
        String dir = (String) request.getSession().getAttribute("dir"); // 分类
        String order = (String) request.getSession().getAttribute("order"); // 排序
        // 如果不是首第一页，就回退一页；是第一页，则不变。
        if(currentPage != 1){
            currentPage--;
            request.getSession().setAttribute("currentPage",currentPage);
        }
        try {
            // 转发到展示Servlet
            request.getRequestDispatcher("/postServlet?method=getPostDetailByDirAndOrder&dir="+dir+"&order="+order+"&currentPage="+currentPage).forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 下一页
    protected void nextPage(HttpServletRequest request, HttpServletResponse response){
        int currentPage = (int) request.getSession().getAttribute("currentPage"); // 当前页
        String dir = (String) request.getSession().getAttribute("dir"); // 分类
        String order = (String) request.getSession().getAttribute("order"); // 排序
        // 判断是否跳页
        int endPage;
        if(total != 0){
            if(total % pageSize == 0){
                endPage = total/pageSize;
            }else{
                endPage = total/pageSize + 1;
            }
            if(currentPage != endPage){
                currentPage ++;
                request.getSession().setAttribute("currentPage",currentPage);
            }
        }
        // 转发
        try {
            // 转发到展示Servlet
            request.getRequestDispatcher("/postServlet?method=getPostDetailByDirAndOrder&dir="+dir+"&order="+order+"&currentPage="+currentPage).forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
