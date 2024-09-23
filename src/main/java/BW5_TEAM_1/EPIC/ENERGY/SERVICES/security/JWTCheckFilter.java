package BW5_TEAM_1.EPIC.ENERGY.SERVICES.security;


import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.User;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.UnauthorizedException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.services.UsersService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTCheckFilter extends OncePerRequestFilter {

    @Autowired
    JWTTools jwtTools;

    @Autowired
    UsersService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("INSERT DATA CORRECTLY");
        String accessToken = authHeader.substring(7);
        jwtTools.verifyToke(accessToken);
        String id = this.jwtTools.extractIdFromToken(accessToken); // qui abbiamo l'id da inserire nel context
        // ... qui inserire dopo lo user che prendiamo dal DB per aggiungerlo al context
        User userFromDB = this.userService.findById(UUID.fromString(id));
        Authentication auth = new UsernamePasswordAuthenticationToken(userFromDB, null, userFromDB.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }

    @Override
    public boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
