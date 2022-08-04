Feature: Open Street Map

  @searchTheWebsite
  Scenario: Search the website

    Given open Google Chrome Browser
    When search Open Street Map
    Then homepage is displayed

    @openDataEntryForm
    Scenario: Open data entry form

      Given user is on the website
      When click on search button
      Then direction page opens

      @fillingDirectionForm
      Scenario: Filling direction form

        Given user is on direction page
        When enter direction points
        And input transport type
        And click on Go Button
        Then distance is 2425 km








