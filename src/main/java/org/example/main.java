package org.example;

import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args) {
        DatabaseQueryService databaseQueryService =new DatabaseQueryService();
        List<MaxProjectCountClient> maxProjectCountClients=databaseQueryService.finMaxprojectsClient();
        /*
        List<MaxSalaryWorker> maxSalaryWorkers =databaseQueryService.finMaxSalaryworkers();
        List<LongestProject> longestProjects =databaseQueryService.findLongestProject();
        List<YoungestAndEldest> youngestAndEldests =databaseQueryService.findYungestAndEldest();
        List<ProjectPrice> projectPrices =databaseQueryService.projectPrices();*/
       // System.out.println(maxSalaryWorkers.get(0).toString());
        System.out.println(maxProjectCountClients.get(0).toString());
      //  System.out.println(Arrays.toString(new List[]{longestProjects}));
       // System.out.println(Arrays.toString(new List[]{youngestAndEldests}));
       // System.out.println(Arrays.toString(new List[]{projectPrices}));
        databaseQueryService.clear();
    }
}
