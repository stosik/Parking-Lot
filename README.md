# Parking-Lot

## Example of Hexagonal architecture for system for managing parking places.

## Prerequisites
There is one entry point for module which is exposed, the rest of api is kept package private to its own.

# Modules

reservations:
- start park meter as a driver
- stop park meter as a driver
- ask for price of reservation
- check whether vehicle started park meter as operator
- check how much you earned as owner

# Food for thought:
1. In real systems with park meter it is doubtful to store such info as driver information, the only thing the system cares about is your license plate 
and maybe type of your cart to calculate cost in the end (abstracted it as CreateReservationCommand coming from outside information).

### Run
##### Manual (local) 
```bash
# start backend
# from other just run:
./startup.sh
```
> Access REST API at http://localhost:8080
