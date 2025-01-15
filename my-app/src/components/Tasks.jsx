import React, { useEffect, useState } from 'react';
import axios from 'axios';

function Tasks() {
    const [tasks, setTasks] = useState([]);

    useEffect(() => {
        const fetchTasks = async () => {
            try {
                const response = await axios.get('http://localhost:8080/tasks/{email}');
                setTasks(response.data);
            } catch (error) {
                console.error('Failed to fetch course:', error.response?.data || error.message);
            }
        };

        fetchTasks();
    }, []);

    return (
        <div>
            <h3>Tasks</h3>
            <ul>
                {tasks.map((task, index) => (
                    <li key={index}>{task.name}</li>
                ))}
            </ul>
        </div>
    );
}

export default Tasks;
