package hello.servlet.web.frontcontroller;

import hello.servlet.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class ModelView {

    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    public ModelView(String members, List<Member> members1) {
    }
}
