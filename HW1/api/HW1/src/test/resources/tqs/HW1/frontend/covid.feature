Feature: Get Covid Info

    Scenario: Search for Italy covid status
        Given the following page "http://localhost:8000/"
        When I select "Italy" in the country select
        Then I should see the recent and 6 months before covid details about "Italy"

    Scenario: Looking for cache details
        Given the following page "http://localhost:8000/cache.html"
        Then the title should be "Cache Details"