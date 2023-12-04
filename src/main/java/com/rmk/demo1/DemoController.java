package com.rmk.demo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@WebFilter("/*")
@Slf4j
public class DemoController implements Filter {

    @Autowired EmployeeSvc employeeSvc;
    @GetMapping("/")
    public String welcome(){
            return "Kem cho!";
    }

    @GetMapping("/ping")
    public String ping(){
        return "Hola!";
    }

    @GetMapping("/greet")
    public String greet(@RequestParam (value = "name") String name) {return "Kem cho; "+ name;}

    @GetMapping(value = "/emp/all")
    public ResponseEntity<List<Employee>> getAllEmp(){
        return ResponseEntity.ok(this.employeeSvc.getAll());
    }

    @PostMapping("/emp/add")
    public ResponseEntity<Employee> addEmp(@RequestBody Employee employee){
        return ResponseEntity.ok(this.employeeSvc.add(employee));
    }

    @PostMapping("/emp/bulkadd")
    public ResponseEntity<List<Employee>> addEmp(@RequestBody List<Employee> employees){
        return ResponseEntity.ok(employees.parallelStream().map(employee -> this.employeeSvc.add(employee)).collect(Collectors.toList()));
    }


    @AdminUserAnnotation
    @DeleteMapping("/emp/delete/{id}")
    public ResponseEntity<Boolean> deleteEmp(@PathVariable Long id){
        return ResponseEntity.ok(this.employeeSvc.delete(id));
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)  {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader(
                "Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
try{
    filterChain.doFilter(servletRequest, servletResponse);
}catch (RuntimeException | ServletException | IOException e){

    String message= e.getCause().getMessage();
    httpServletResponse.setStatus(500);
    httpServletResponse.setHeader("error",message);
    if(message.contains("~")){
        String code = message.split("~")[0];
        httpServletResponse.setStatus(Integer.parseInt(code));
        httpServletResponse.setHeader("error",message.split("~")[1]);
    }


}




    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
