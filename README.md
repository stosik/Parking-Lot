# Parking-Lot

## Example of Hexagonal architecture for system for managing parking places.

## Prerequisites
There is one entry point for module which is exposed, the rest of api is kept package private to its own.

# Modules

reservations:
- start park meter as a driver
- stop park meter as a driver
- ask for price of reservation as a driver
- check whether vehicle started park meter as operator
- check how much you earned as owner

# Food for thought:
1. In real systems with park meter it is doubtful to store such data as driver information, the only thing the system cares about is your license plate and eventually type of your cart to calculate cost in the end (abstracted it as CreateReservationCommand coming from outside).
2. Normally, we would receive a ticket of some sort to put behind window (abstracted as ReservationDto) with cares such information as id of reservation or date and time it has been started at.
3. We use it once again (providing reservation id) to stop park meter and calculate cost four our completed reservation.
4. Operator should probably check car by the license plate whether the park meter has been started for it or not.
5. Owner takes care of gathering earnings, he should be able to have access to information how much the specific day parking has gained. The following assumption has been made:
- by specifying day he want to calculate earning for, the system takes all reservations which stop time is on that specific day

### Run
##### Manual (local) 
```bash
# start backend
# from other just run:
./startup.sh
```
> Access REST API at http://localhost:8080
