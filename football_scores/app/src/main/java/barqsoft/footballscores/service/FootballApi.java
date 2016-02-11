package barqsoft.footballscores.service;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

/**
 * Created by ivan on 2/12/16.
 */
public interface FootballApi {

    @GET("/alpha/teams/{team_id}")
    Call<TeamInfoResponse> getTeamInformation(@Header("X-Auth-Token") String apiKey, @Path("team_id") String teamId);

}