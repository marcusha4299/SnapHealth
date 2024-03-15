# Snaphealth
## FOOD RECOMMENDATION
### Data Collect: 
#### User: age, gender, weight, height, level activity, goal, diet type

#### Calculte BMR by Harris-Benedict equation: https://www.ncbi.nlm.nih.gov/pmc/articles/PMC7784146/#:~:text=In%20men%2C%20the%20Harris%2DBenedict,4.6756%20x%20age%20in%20years.
##### Men:  BMR = 66.4730+(13.7516*weight in kg) + (5.0033*height in cm) - (6.7550*age in years)
##### Women:BMR = 655.0955+(9.5634*weight in kg) + (1.8496*height in cm) -(4.6756*age in years)

#### Calculte Activity level by Harris-Benedict equation to factor in activity level: https://www.livestrong.com/article/526442-the-activity-factor-for-calculating-calories-burned/
Sedentary (little or no exercise): BMR × 1.2
Lightly active (light exercise/sports 1-3 days/week): BMR × 1.375
Moderately active (moderate exercise/sports 3-5 days/week): BMR × 1.5
Very active (hard exercise/sports 6-7 days a week): BMR × 1.725
Extremely active (very hard exercise/sports & physical job or 2x training): BMR × 1.9

#### Calculate Total Daily Energy Expenditure (TDEE):
TDEE = BMR × Activity Level

#### Determine Caloric Intake for Goals:
To lose weight: Subtract a certain number of calories (usually 500 to 1000) from your TDEE per day for a gradual weight loss of about 1-2 pounds per week.
To maintain weight: Keep your caloric intake equal to your TDEE.
To gain weight: Add a certain number of calories (usually 500 to 1000) to your TDEE per day for a gradual weight gain.

#### Calculate Macronutrient Distribution:
Protein (g) = 1 calories
Fat (g): 4 calories)
Carbohydrates (g): 4 calories)



## THE HYDRATION TRACKER
##### 4 options for user:
Small Gulp : 43ml
Large Gulp : 77ml
Five Mouthfuls : 157ml
A mug : 240ml
###### research : https://pubmed.ncbi.nlm.nih.gov/23323807/#:~:text=The%20mean%20volume%20(95%25%20prediction,(25%2D375)%20mL.

Recommendation water intake by age and gender 
###### research : https://www.betterhealth.vic.gov.au/health/healthyliving/water-a-vital-nutrient
Date and time tracker: API -> track current date -> If the current past (tommorow) -> reset 

## SLEEP TRACKER 
Sleep recommendation by age
###### research: https://www.cdc.gov/sleep/about_sleep/how_much_sleep.html
Compare average sleep duration with sleep recommendation (error within +- 1 hours)
    
