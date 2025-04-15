🏆  **Automatisation du provisioning des outils d’ingénierie plateforme**

📌 Automatisation du provisioning des outils d’ingénierie plateforme à partir du référentiel des produits

### 📖 **Description**

Actuellement, le provisioning des outils d’ingénierie plateforme (GitLab, Artifactory, Observability avec Grafana,
Kubernetes Namespaces, etc.) est **manuel** (scripts python), ce qui entraîne :

- Une perte de temps pour les équipes (création et configuration manuelles).
- Un risque d’erreurs et un manque de cohérence entre les environnements.
- Une faible scalabilité et une complexité accrue en cas de changements.

L’objectif de cette épic est d’**automatiser totalement le provisioning** des outils d’ingénierie **en se basant sur le
référentiel des produits**, qui deviendra la **source unique de vérité**. Toute modification dans ce référentiel
déclenchera automatiquement la création ou la mise à jour des ressources.

### **🚀 Objectifs**

✅ Exploiter le référentiel des produits comme **source unique** pour le provisioning des outils.  
✅ Automatiser la création et la configuration de GitLab, Artifactory, Grafana et Kubernetes Namespaces.  
✅ Assurer la cohérence entre les environnements grâce à une approche **Infrastructure as Code (IaC)**.  
✅ Intégrer un pipeline CI/CD pour déployer automatiquement les configurations.

### 📌 **Critères de succès**

- **100 % du provisioning automatisé** via le référentiel des produits.
- Suppression des interventions manuelles pour la création des outils.
- **Synchronisation en temps réel** entre le référentiel et les plateformes.
- Documentation et monitoring mis en place pour assurer le suivi des déploiements.

---

### 📝 **User Stories associées**

🔹 **[TODO]

🔹 **[TODO]

---

### 📅 **Priorisation et Roadmap**

⏳ **Priorité : [TODO]
👨‍💻 **Responsable : [Chaker FEZAI]**  
🛠 **Technos : Terraform/Python, GitLab CI/CD, Webhooks** , Helm, Kubernetes
🎯 **Livraison prévue : Sprint XX (Q1 2025)**


---

### ⏳ **Estimation**

| Tâche                                                                                                   | Estimation (J/H) |
|---------------------------------------------------------------------------------------------------------|------------------|
| Conception de l’architecture et choix des outils                                                        | 5 jours          |
| Développement du provisioning GitLab                                                                    | 3 jours          |
| Ajouter le mapping des groupes Okta au groupes Gitlab via SAML (SMAL Link)                              | 2 jours          |
| <span style="background:#d3f8b6">Développement du provisioning Kubernetes (Namespaces, RBAC)????</span> | 3 jours          |
| Développement du provisioning Artifactory                                                               | 3 jours          |
| Développement du provisioning Grafana                                                                   | 3 jours          |
| Intégration avec le référentiel des produits                                                            | 1 jours          |
| Déploiement et tests en environnement de staging                                                        | 3 jours          |
| Documentation et formation des équipes                                                                  | 3 jours          |

🟢 **Total estimé : ~21 jours-hommes**

---

💡 **Notes complémentaires** :

- Un **PoC** sera réalisé pour valider l’intégration entre le **référentiel des produits et les outils de provisioning
  **.
- Un mécanisme de **rollback automatique** sera étudié en cas d’échec de provisioning.


