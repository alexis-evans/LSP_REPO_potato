# CRC Cards – ATC System

## Class: Aircraft
**Responsibilities:**
- Collect/hold data such as aircraft type and flight data (flight number, ETA, current altitude, etc.)
- Pass data to Transponder
- Execute course corrections received from Transponder

**Collaborators (if any):**
- Transponder

**Assumptions (if any):**
- Every aircraft has its own Transponder


---

## Class: Transponder
**Responsibilities:**
- Receive Aircraft’s real-time data
- Format and broadcast aircraft data in high-density packet format to ATC Ground Station
- Receive safety warnings from ATC Ground Station and pass them to Aircraft

**Collaborators (if any):**
- Aircraft
- ATC Ground Station

**Assumptions (if any):**
- Every Transponder belongs to one Aircraft


---

## Class: ATC Ground Station
**Responsibilities:**
- Receive data from Transponder
- Unpack/parse packet data into usable format
- Store processed data into the Aircraft Database
- Send safety alerts from Safety Analyzer to Transponder

**Collaborators (if any):**
- Transponder
- Aircraft Database
- Safety Analyzer

**Assumptions (if any):**
- N/A


---

## Class: Aircraft Database
**Responsibilities:**
- Store current and historical aircraft data
- Provide data for the Controller Graphics Display
- Support queries from Controller for specific aircraft details
- Provide data for Safety Analyzer


**Collaborators (if any):**
- ATC Ground Station
- Controller Graphics Display
- Safety Analyzer
- Controller

**Assumptions (if any):**
- N/A


---

## Class: Controller Graphics Display
**Responsibilities:**
- Fetch updated flight info from the database and refresh visual interface every 10 seconds
- Provide user interface for controller-initiated queries on Aircraft Database
- Always listen for System Override signal from the Safety Analyzer that interrupts regular 10-second refreshes
- Display alerts for dangerous situations immediately
- Show results of Controller-initiated aircraft queries

**Collaborators (if any):**
- Aircraft Database
- Safety Analyzer
- Controller

**Assumptions (if any):**
- N/A


---

## Class: Safety Analyzer
**Responsibilities:**
- Continuously scan flight data for dangerous situations and conflicts
- Generate and trigger visual and audible alerts for the Controller
- Issue a System Override signal to the Controller Graphics Display during emergencies

**Collaborators (if any):**
- Aircraft Database
- Controller Graphics Display

**Assumptions (if any):**
- N/A


---

## Class: Controller
**Responsibilities:**
- Monitor aircraft information shown on Controller Graphics Display
- Request details about a selected aircraft
- Interpret alerts and warnings produced by the system
- Initiate responses to dangerous situations based on displayed information

**Collaborators (if any):**
- Controller Graphics Display
- Aircraft Database

**Assumptions (if any):**
- N/A

