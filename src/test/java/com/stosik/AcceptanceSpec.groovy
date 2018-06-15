package com.stosik

class AcceptanceSpec
{
    static def "renting scenarios"()
    {
        when: 'As a driver, I start the parking meter, so I don’t have to pay the fine for the invalid parking.'
        then: 'The parking meter is started, datetime of reservation is set .'

        when: 'As a parking operator, I check if the vehicle has started the parking meter.'
        then: 'I receive information whether park meter for vehicle has been started (true/false).'

        when: 'As a driver, I want to stop the parking meter, so that I pay only for the actual parking time'
        then: 'The parking meter is stopped, and reservation stop time is set.'

        when: 'As a driver, I want to know how much I have to pay for parking.'
        then: 'Giving an id of reservation I receive cost of reservation.'

        when: 'As a parking owner, I specify day I would like system to calculate earnings for.'
        then: 'He receives takings for specific day with '
    }
}
