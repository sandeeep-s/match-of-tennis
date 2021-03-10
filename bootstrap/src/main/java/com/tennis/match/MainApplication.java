package com.tennis.match;

import com.tennis.match.application.MatchApplicationService;
import com.tennis.match.domain.model.Match;
import com.tennis.match.domain.model.MatchId;
import com.tennis.match.domain.model.MatchRepository;
import com.tennis.match.port.adapters.api.MatchScoreManagerApi;
import com.tennis.match.port.adapters.services.InMemoryMatchRepository;

import java.util.HashMap;
import java.util.Map;

public class MainApplication {

    private final MatchScoreManagerApi matchScoreManagerApi;

    public MainApplication(MatchScoreManagerApi matchScoreManagerApi) {
        this.matchScoreManagerApi = matchScoreManagerApi;
    }

    public static void main(String[] args) {
        try {
            MainApplication application = bootstrap();

        } catch (Exception e) {
            System.out.println(String.format("ERROR: Conference could not be scheduled. %s.", e.getMessage()));
            e.printStackTrace();
            System.exit(1);
        }

    }

    private static MainApplication bootstrap() {

        Map<MatchId, Match> matchStore = new HashMap<>();
        MatchRepository matchRepository = new InMemoryMatchRepository(matchStore);
        MatchApplicationService matchApplicationService = new MatchApplicationService(matchRepository);
        MatchScoreManagerApi matchScoreManagerAPI = new MatchScoreManagerApi(matchApplicationService);

        return new MainApplication(matchScoreManagerAPI);
    }


}
