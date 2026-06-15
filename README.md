# Multi-Agent Systems: SoccerBots Team 🤖⚽

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![TeamBots](https://img.shields.io/badge/TeamBots_API-005C84?style=for-the-badge&logo=robotframework&logoColor=white)
![AI](https://img.shields.io/badge/Multi--Agent_Systems-8A2BE2?style=for-the-badge&logo=probot&logoColor=white)

> **Academic context:** This project was completed in 2022 as part of the *Distributed Artificial Intelligence* course (Multi-Agent Systems & Reinforcement Learning) — MSc in Computer Science, Université de Caen Normandie.
> 🏆 **Ranked 2nd out of all teams in class** — Grade: 18/20

---

## Project Overview

This project consists of developing a fully autonomous **SoccerBots team** under the [TeamBots simulator](https://www.cs.cmu.edu/~trb/TeamBots/#APPLICATIONS) — a multi-robot simulation environment where teams of agents compete in a simplified soccer game.

The project is a practical application of concepts studied in the Distributed Artificial Intelligence module, including:

- **Multi-agent architectures** — designing agents that coordinate without centralized control
- **Subsumption architecture** — layering reactive behaviors by priority
- **Attraction-repulsion strategies** — defining spatial interaction rules between agents
- **Reinforcement Learning principles** — iteratively refining agent behaviors based on performance outcomes

Each agent (robot player) operates autonomously, perceiving its local environment and making decisions in real time based on its programmed behavioral strategy.

---

## Strategy & Agent Design

The team's intelligence relies on a combination of **reactive** and **deliberative** behaviors:

- **Role assignment** — each agent is assigned a dynamic role (attacker, defender, goalkeeper) based on its position relative to the ball and goal
- **Subsumption layers** — lower-priority behaviors (obstacle avoidance, positioning) are overridden by higher-priority ones (ball pursuit, shooting) when triggered
- **Attraction-repulsion** — agents maintain spatial cohesion while avoiding clustering, ensuring field coverage
- **Emergent coordination** — cooperative behavior arises from individual agent rules without explicit communication, a hallmark of decentralized multi-agent systems

This strategy proved robust enough to **outperform most competing teams** during the in-class tournament, securing **2nd place** overall.

---

## Results

![Simulation result](https://github.com/thiouneba/soccerbots_multi_agent/blob/main/resultImg.PNG)

---

## How to Run

1. Download and install the TeamBots API — instructions available [here](https://www.cs.cmu.edu/~trb/TeamBots/#APPLICATIONS)
2. Navigate to the `Soccerbots/` directory
3. Compile the project:
   ```bash
   make all
   ```
4. Launch the simulation:
   ```bash
   ./demo
   ```

You are welcome to use this team as a reference or baseline, and pit your own team against it.

---

## Tech Stack

| Tool | Role |
|------|------|
| Java | Agent implementation and behavioral logic |
| TeamBots API | Multi-robot soccer simulation environment |

---

## Conclusion

This project provided hands-on experience with the core challenges of multi-agent system design: emergent coordination, behavioral trade-offs, and the limits of purely reactive strategies.

One key takeaway is that no fixed strategy is universally optimal in a competitive multi-agent environment — an opponent with a sufficiently different behavioral profile can always find an exploit. The logical next step would be to develop an **adaptive team** capable of observing the opponent's patterns in real time and adjusting its strategy accordingly. Such a system would combine reactive multi-agent design with higher-level learning, potentially using reinforcement learning to evolve agent policies across matches.

Despite this limitation, our strategy demonstrated strong competitive performance, with agents exhibiting non-trivial, coordinated behaviors that consistently challenged opposing teams throughout the tournament.

---

## References

- Brooks, R.A. (1986). A robust layered control system for a mobile robot. *IEEE Journal on Robotics and Automation*, 2(1), 14–23. *(Subsumption architecture)*
- Weiss, G. (Ed.) (1999). *Multiagent Systems: A Modern Approach to Distributed Artificial Intelligence*. MIT Press.
- TeamBots Documentation — Carnegie Mellon University: https://www.cs.cmu.edu/~trb/TeamBots/
