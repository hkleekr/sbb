package com.example.sbb;

import com.example.sbb.Service.AnswerService;
import com.example.sbb.Service.QuestionService;
import com.example.sbb.domain.*;
import com.example.sbb.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    @Autowired
    private final QuestionRepository questionRepository;

//    public MainController(QuestionService questionService) {
//        this.questionService = questionService;
//    }

    //    root_URL
    @RequestMapping("/")
    public String root() {
        return "redirect:/sbb";
    }

//    메인 페이지
    @GetMapping("/sbb")
    public String list(Model model, @PageableDefault(size = 8, sort="id", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText) {
        Page<Question> q = questionRepository.findBySubjectContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, q.getPageable().getPageNumber() -4);
        int endPage = Math.min(q.getTotalPages(), q.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("q", q);
        return "board";
    }

//    질문 페이지
    @GetMapping("/sbb/question") // 빈 페이지로 이동
    public String questionPage(Model model) {
        model.addAttribute("questionPage", new Question());
        return "question_form";
    }

//    질문 저장
    @PostMapping("/sbb/question")
    public ResponseEntity<QuestionResponseDto> questionSummit(QuestionRequestDto questionRequestDto) {
        QuestionResponseDto questionResponseDto = new QuestionResponseDto(); //새로운 응답객체를 생성
        questionService.save(questionRequestDto, questionResponseDto);
        return ResponseEntity.ok().body(questionResponseDto);
    }

//    질문 수정
    @PutMapping("/sbb/question")
    public ResponseEntity<QuestionResponseDto> questionModifySummit(@RequestParam ("id") Integer id, QuestionRequestDto questionRequestDto, QuestionResponseDto questionResponseDto){
        questionService.modify(id, questionRequestDto, questionResponseDto);
        return ResponseEntity.ok().body(questionResponseDto);
    }

//    질문 삭제
    @DeleteMapping("sbb/question")
    public ResponseEntity<QuestionResponseDto> questionDeleteSummit(@RequestParam ("id") Integer id, QuestionResponseDto questionResponseDto) {
        questionService.delete(id, questionResponseDto);
        return ResponseEntity.ok().body(questionResponseDto);
    }

//    답변 페이지
    @GetMapping("/sbb/detail")
    public String answerPage(Model model, @RequestParam Integer id) {
        Question q = (Question) questionService.getDetail(id);
        model.addAttribute("questionDetail", q);
        return "answer_detail";
    }

//    답변 저장
    @PostMapping("/sbb/detail")
    public ResponseEntity<AnswerResponseDto> answerSummit(AnswerRequestDto answerRequestDto) {
        AnswerResponseDto answerResponseDto = new AnswerResponseDto();
        answerService.save(answerRequestDto, answerResponseDto);
        return ResponseEntity.ok().body(answerResponseDto);
    }

//    답변 수정
    @PutMapping("/sbb/detail")
    public ResponseEntity<AnswerResponseDto> answerModifySummit(@RequestParam ("id") Integer id, AnswerRequestDto answerRequestDto, AnswerResponseDto answerResponseDto) {
        answerService.modify(id, answerRequestDto, answerResponseDto);
        return ResponseEntity.ok().body(answerResponseDto);
    }

//    답변 삭제
    @DeleteMapping("/sbb/detail")
    public ResponseEntity<AnswerResponseDto> answerDeleteSummit(@RequestParam ("id") Integer id, AnswerResponseDto answerResponseDto) {
        answerService.delete(id, answerResponseDto);
        return ResponseEntity.ok().body(answerResponseDto);
    }

}
