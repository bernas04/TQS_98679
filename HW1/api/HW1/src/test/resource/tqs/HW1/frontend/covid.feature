Feature: Get Covid Info

    Scenario: Search for Italy covid status
        When I open the browser and access the page "https://localhost:8000"
        And I select "Italy" in the country select
        Then I should see the recent and 6 months before covid details about "Italy"