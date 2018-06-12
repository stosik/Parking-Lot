package com.stosik

class AcceptanceSpec
{
    static def "renting scenarios"()
    {

        when: 'As a driver, I start the parking meter, so I don’t have to pay the fine for the invalid parking.'
        then: 'The parking meter is started, datetime of reservation is created .'

        when: 'As a parking operator, I check if the vehicle has started the parking meter.'
        then: ''

        when: 'As a driver, I want to stop the parking meter, so that I pay only for the actual parking time'
        then: ''

        when: 'As a driver, I want to know how much I have to pay for parking.'
        then: ''

        when: 'As a parking owner, I want to know how much money was earned during a given day.'
        then: ''
    }
}
