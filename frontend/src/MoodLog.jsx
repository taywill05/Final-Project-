import {useState } from 'react';
import {createMood} from './api';
import './MoodLog.css';
import { apiSend } from './components/authApi';

function MoodLog() {    

    const [mood, setMood] = useState('');
    const [notes, setNotes] = useState('');
    const [status, setStatus] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        if(!mood) {
            setStatus('Please select a mood.');
            return;
        }
        const moodEntry = {
            mood,
            note: notes,
            date: new Date().toISOString()
        };

        //try {
            await axios.post('/mood/add', moodEntry).then(response => {
                console.log('Mood logged:', response.data);
            }).catch(error => {
                console.error('Error logging mood:', error);
            });
            //await createMood(moodEntry);
           // const data = await apiSend("/mood/add", "POST", moodEntry);
            //setStatus(data.message || 'Mood response not received!');
            
           /* setTimeout(() => { 
            setMood('');
        setNotes('');
            }, 3000);

        } catch (error) {
            console.error( error);
            setStatus('Error logging mood. Please try again.');
        }*/
    };

    return (    
        
        <div className="moodlog-container">
        <h1>Log Your Mood</h1>
        
        <form onSubmit={handleSubmit} className="moodlog-form">

            <label>
                Select Mood:
                <select value={mood} onChange={(e) => setMood(e.target.value)} required>
                    <option value="">--Choose your mood--</option>
                    <option value="happy">😊 Happy</option>
                    <option value="sad">😔 Sad</option>
                    <option value="angry">😡 Angry</option>
                    <option value="surprised">😲 Surprised</option>
                    <option value="neutral">😐 Neutral</option>
                    <option value="excited">🤩 Excited</option>
                    <option value="anxious">😰 Anxious</option>
                    <option value="grateful">🙏 Grateful</option>
                    <option value="tired">😴 Tired</option> 
                    <option value="stressed">😣 Stressed</option>
                    <option value="confused">😕 Confused</option>
                    <option value="bored">😐 Bored</option>
                    <option value="lonely">😞 Lonely</option>
                    <option value="hopeful">🌈 Hopeful</option>
                    <option value="relaxed">😌 Relaxed</option>
                    <option value="proud">😎 Proud</option>
                    <option value="curious">🤔 Curious</option>
                    <option value="motivated">💪 Motivated</option>
                    <option value="overwhelmed">😵 Overwhelmed</option>
                    <option value="joyful">😂 Joyful</option>
                    <option value="peaceful">☮️ Peaceful</option>
                    <option value="frustrated">😤 Frustrated</option>

                </select>
            </label>

            <label>
                Notes:
                <textarea value={notes} onChange={(e) => setNotes(e.target.value)} />
            </label>
            <button type="submit">Log Mood</button>
        </form>
        {status && <p className="status-message">{status}</p>}
        </div>    
    ); 
}

export default MoodLog;