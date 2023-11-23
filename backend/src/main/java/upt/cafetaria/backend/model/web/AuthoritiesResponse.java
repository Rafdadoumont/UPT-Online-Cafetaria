package upt.cafetaria.backend.model.web;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import upt.cafetaria.backend.model.enums.RoleEnum;

@Getter
@Setter
@Builder
public class AuthoritiesResponse {
    private RoleEnum role;
}
